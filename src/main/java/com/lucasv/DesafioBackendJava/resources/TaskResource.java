package com.lucasv.DesafioBackendJava.resources;

import com.lucasv.DesafioBackendJava.entities.Task;
import com.lucasv.DesafioBackendJava.entities.enums.TaskPriorityStatus;
import com.lucasv.DesafioBackendJava.entities.enums.TaskStatus;
import com.lucasv.DesafioBackendJava.services.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(tags = "tasks")
@RequestMapping(value = "/tasks")
public class TaskResource {

    @Autowired
    private TaskService service;

    @GetMapping(value = "/{taskps}")
    @ApiOperation(value = "${TaskResource.listTasks}")
    public ResponseEntity<List<Task>> listTasks(@PathVariable("taskps") TaskPriorityStatus taskPriorityStatus){
        if(taskPriorityStatus!=null){
            List<Task> list = service.findAll().stream().filter(x -> x.getTaskStatus() != TaskStatus.CONCLUIDO && x.getTaskPriorityStatus()==taskPriorityStatus).collect(Collectors.toList());
            return ResponseEntity.ok().body(list);
        }
        List<Task> list = service.findAll().stream().filter(x -> x.getTaskStatus() != TaskStatus.CONCLUIDO).collect(Collectors.toList());
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    @ApiOperation(value = "${TaskResource.insert}")
    public ResponseEntity<Task> insert(@RequestBody Task obj){
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "${TaskResource.delete}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    @ApiOperation(value = "${TaskResource.update}")
    public ResponseEntity<Task> update(@PathVariable Long id, @RequestBody Task obj){
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

    @PutMapping("/status/{id}")
    @ApiOperation(value = "${TaskResource.updateStatus}")
    public ResponseEntity<Task> updateStatus(@PathVariable Long id, @RequestBody Task obj){
        obj = service.updateStatus(id,obj);
        return ResponseEntity.ok().body(obj);
    }
}
