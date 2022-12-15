package com.lucasv.DesafioBackendJava.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.context.request.ServletWebRequest;

class GlobalExceptionHandlerControllerTest {
    /**
     * Method under test: {@link GlobalExceptionHandlerController#errorAttributes()}
     */
    @Test
    void testErrorAttributes() {
        ErrorAttributes actualErrorAttributesResult = (new GlobalExceptionHandlerController()).errorAttributes();
        assertNull(actualErrorAttributesResult.getError(new ServletWebRequest(new MockHttpServletRequest())));
    }

    /**
     * Method under test: {@link GlobalExceptionHandlerController#handleCustomException(HttpServletResponse, CustomException)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testHandleCustomException() throws IOException {

        GlobalExceptionHandlerController globalExceptionHandlerController = new GlobalExceptionHandlerController();
        Response res = new Response();
        globalExceptionHandlerController.handleCustomException(res,
                new CustomException("An error occurred", HttpStatus.CONTINUE));
    }

    /**
     * Method under test: {@link GlobalExceptionHandlerController#handleCustomException(HttpServletResponse, CustomException)}
     */
    @Test
    void testHandleCustomException2() throws IOException {
        GlobalExceptionHandlerController globalExceptionHandlerController = new GlobalExceptionHandlerController();
        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();
        globalExceptionHandlerController.handleCustomException(mockHttpServletResponse,
                new CustomException("An error occurred", HttpStatus.CONTINUE));
        assertTrue(mockHttpServletResponse.isCommitted());
        assertEquals(100, mockHttpServletResponse.getStatus());
        assertEquals("An error occurred", mockHttpServletResponse.getErrorMessage());
    }

    /**
     * Method under test: {@link GlobalExceptionHandlerController#handleCustomException(HttpServletResponse, CustomException)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testHandleCustomException3() throws IOException {

        GlobalExceptionHandlerController globalExceptionHandlerController = new GlobalExceptionHandlerController();
        globalExceptionHandlerController.handleCustomException(new Response(), null);
    }

    /**
     * Method under test: {@link GlobalExceptionHandlerController#handleCustomException(HttpServletResponse, CustomException)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testHandleCustomException4() throws IOException {

        GlobalExceptionHandlerController globalExceptionHandlerController = new GlobalExceptionHandlerController();

        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();
        mockHttpServletResponse.setCommitted(true);
        globalExceptionHandlerController.handleCustomException(mockHttpServletResponse,
                new CustomException("An error occurred", HttpStatus.CONTINUE));
    }

    /**
     * Method under test: {@link GlobalExceptionHandlerController#handleAccessDeniedException(HttpServletResponse)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testHandleAccessDeniedException() throws IOException {

        GlobalExceptionHandlerController globalExceptionHandlerController = new GlobalExceptionHandlerController();
        globalExceptionHandlerController.handleAccessDeniedException(new Response());
    }

    /**
     * Method under test: {@link GlobalExceptionHandlerController#handleAccessDeniedException(HttpServletResponse)}
     */
    @Test
    void testHandleAccessDeniedException2() throws IOException {
        GlobalExceptionHandlerController globalExceptionHandlerController = new GlobalExceptionHandlerController();
        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();
        globalExceptionHandlerController.handleAccessDeniedException(mockHttpServletResponse);
        assertTrue(mockHttpServletResponse.isCommitted());
        assertEquals(403, mockHttpServletResponse.getStatus());
        assertEquals("Acesso negado", mockHttpServletResponse.getErrorMessage());
    }

    /**
     * Method under test: {@link GlobalExceptionHandlerController#handleAccessDeniedException(HttpServletResponse)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testHandleAccessDeniedException3() throws IOException {

        GlobalExceptionHandlerController globalExceptionHandlerController = new GlobalExceptionHandlerController();

        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();
        mockHttpServletResponse.setCommitted(true);
        globalExceptionHandlerController.handleAccessDeniedException(mockHttpServletResponse);
    }

    /**
     * Method under test: {@link GlobalExceptionHandlerController#handleException(HttpServletResponse)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testHandleException() throws IOException {

        GlobalExceptionHandlerController globalExceptionHandlerController = new GlobalExceptionHandlerController();
        globalExceptionHandlerController.handleException(new Response());
    }

    /**
     * Method under test: {@link GlobalExceptionHandlerController#handleException(HttpServletResponse)}
     */
    @Test
    void testHandleException2() throws IOException {
        GlobalExceptionHandlerController globalExceptionHandlerController = new GlobalExceptionHandlerController();
        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();
        globalExceptionHandlerController.handleException(mockHttpServletResponse);
        assertTrue(mockHttpServletResponse.isCommitted());
        assertEquals(400, mockHttpServletResponse.getStatus());
        assertEquals("Algo deu errado", mockHttpServletResponse.getErrorMessage());
    }

    /**
     * Method under test: {@link GlobalExceptionHandlerController#handleException(HttpServletResponse)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testHandleException3() throws IOException {

        GlobalExceptionHandlerController globalExceptionHandlerController = new GlobalExceptionHandlerController();

        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();
        mockHttpServletResponse.setCommitted(true);
        globalExceptionHandlerController.handleException(mockHttpServletResponse);
    }
}

