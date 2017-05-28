package com.andrius.project.AsynchronousExample1.servlets;

import java.io.IOException;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// using annotation we created servlet that supports asychronous proccesing: 
@WebServlet(asyncSupported = true, urlPatterns = { "/MyServlet1" })
public class MyServlet1 extends HttpServlet {
	// FIELDS:
	private static final long serialVersionUID = 1L;
  
    public MyServlet1() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// we using javax.servlet.ServletRequest startAsync() to get AsyncContext object that
		// will have original request and response object, unless you use 2 argument method that
		// you can pass your wrapper servlet request and response object, for now using original no-argument method:
		final AsyncContext ac = request.startAsync();
		// now when we have a async context object we can start doing async job mode
		// with Runnable implementation, there we using anounimous:
		ac.start(new Runnable() {
			
			@Override
			public void run() {
				ac.dispatch("/page.html");
				// ac.complete(); // will cause IllegalStateException, as request already dispatched
			}
		});
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
