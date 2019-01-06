package com.pugpro.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pugpro.DAO.AttendingDAO;
import com.pugpro.DAO.EventDAO;
import com.pugpro.DAO.InstanceDAO;
import com.pugpro.DAO.UserDAO;
import com.pugpro.beans.Event;
import com.pugpro.beans.Instance;
import com.pugpro.beans.User;

@Controller
public class HomeController {
	
	@RequestMapping(value = {"/","/login"})
	public ModelAndView displayLogin(HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("login");
		UserDAO dao = new UserDAO();
		
		//if there is an active session, go to the home page
		if (session.getAttribute("user") != null) {
			mav = displayHome(session); //prepare home page
			mav.setViewName("home"); //go to home page
			return mav;
		}
		
		String email = request.getParameter("email");
		if (email != null) { //if email parameter exists,
			User user = dao.getUserByEmail(email); //attempt to retrieve user with that email
			if (user != null) { //if user exists,
				if (dao.validateUser(request.getParameter("password"),user)) { //if password is correct
					session.setAttribute("user", user); //create session attribute for the user
					mav = displayHome(session); //prepare home page
					mav.setViewName("home"); //go to home page
				} else { //if password is incorrect
					mav.addObject("message","Incorrect password.");
				}
				
			} else { //user does not exist,
				mav.addObject("message","User does not exist.");
			}
		}
		return mav;
	}
	
	@RequestMapping(value="/logout")
	public ModelAndView logout(HttpSession session) {
		ModelAndView mav = new ModelAndView("login");
		session.removeAttribute("user"); //destroy user session attribute
		return mav;
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET) //for when the user initially visits the page
	public ModelAndView displayRegister(HttpSession session, HttpServletResponse response,
			@ModelAttribute("user") User user) throws IOException{
		ModelAndView mav = new ModelAndView();
		
		//if there is an active session, redirect to the home page
		if (session.getAttribute("user") != null) { 
			response.sendRedirect("home");
			return mav;
		} 
		//just display the page, nothing else to do
		mav.setViewName("register");
		return mav;
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST) //for when an attempt has been made to submit the form
	public ModelAndView createUser(HttpSession session, HttpServletRequest request, HttpServletResponse response, 
			@Valid @ModelAttribute("user") User user, BindingResult result) throws IOException {
		UserDAO dao = new UserDAO();
		ModelAndView mav = new ModelAndView();
		
		//if there is an active session, redirect to the home page
		if (session.getAttribute("user") != null) { 
			response.sendRedirect("home");
			return mav;
		} 

		if (!user.getPassword().equals(request.getParameter("confirmPassword"))) { //if the passwords do not match,
			result.addError(new ObjectError("confirmPassword","Passwords do not match.")); //add ObjectError
		} else if (result.hasErrors() ) { //if there were binding errors,	
			mav.setViewName("register"); //stay on the page
		} else if (!(dao.createUser(user.getUsername(), user.getEmail(), user.getPassword()) == null)) { //attempt to create user, and if the user was created successfully,
			user = dao.getUserByEmail(user.getEmail()); //get User object from email
			session.setAttribute("user", user); //create user session attribute
			response.sendRedirect("home"); //redirect to the homepage
		}
		return mav;
	}
	
	@RequestMapping("/home")
	public ModelAndView displayHome(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		EventDAO eventDao = new EventDAO();
		AttendingDAO attendingDao = new AttendingDAO();

		if (session.getAttribute("user") == null) { //if there is no active session,
			mav.setViewName("login"); //display the login page
		} else {
			mav.setViewName("home");
			List<String> attending = attendingDao.getUserEvents( ((User)session.getAttribute("user") ).getUserID() );
			List<Event> events = new ArrayList<Event>();	
			attending.forEach(a -> events.add(eventDao.getEventByID(a) ));
			mav.addObject("events",events);
		}
		return mav;
	}
	
	@RequestMapping(value="/createEvent", method=RequestMethod.GET) //for when the user initially visits the page
	public ModelAndView createEvent(HttpSession session, HttpServletResponse response,
			@ModelAttribute("event") Event event, @ModelAttribute("instance") Instance instance) throws IOException{
		ModelAndView mav = new ModelAndView();
		InstanceDAO dao = new InstanceDAO();
		
		//if there is no active session, display the login page
		if (session.getAttribute("user") == null) { 
			mav.setViewName("login"); 
			return mav;
		}
		
		//create List of instances //TODO THIS SHOULDN'T BE EXECUTE MULTIPLE TIMES PER SESSION OR EVEN APPLICATION
		List<Instance> instances = new ArrayList<Instance>();
		dao.getAllInstances().forEach( i -> instances.add(i));
		session.setAttribute("instances",instances);
		
		//display the page
		mav.setViewName("createEvent");
		return mav;
	}
	
	@RequestMapping(value="/createEvent", method=RequestMethod.POST) //for when an attempt has been made to submit the form
	public ModelAndView createEvent(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			@Valid@ModelAttribute("event") Event event, BindingResult result, @ModelAttribute("instance") Instance instance) throws IOException {
		ModelAndView mav = new ModelAndView();
		EventDAO eventDao = new EventDAO();
		InstanceDAO instanceDao = new InstanceDAO();
		
		//if there is no active session, display the login page
		if (session.getAttribute("user") == null) { 
			mav.setViewName("login"); 
			return mav;
		}
		
		if(result.hasErrors()) {
			mav.setViewName("createEvent");
		} else {
			String userID =  ((User)session.getAttribute("user") ).getUserID();
			String instanceID = instanceDao.getInstanceByName(request.getParameter("instances")).getInstanceID();
			String eventTime = event.getEventDate() + " " + event.getEventTime() + ":00" ;
			System.out.println(event.getEventDate() + " " + event.getEventTime() + ":00" );
			String eventID = eventDao.createEvent( userID, instanceID, //attempt to create event
					event.getTitle(), event.getMinilvl(), event.getDescription(), eventTime  );
			if (eventID != null ) { //if the event was created successfully,
				System.out.println("success");
				response.sendRedirect("eventPage/" + eventID); 
			}
		}
		return mav;
	}
	
	@RequestMapping(value="/viewEvents")
	public ModelAndView displayEvents() {
		ModelAndView mav = new ModelAndView("events");
		EventDAO eventDao = new EventDAO();
		InstanceDAO instanceDao = new InstanceDAO();
		
		//create Map with instanceID to instanceName and add it to the model
		List<Instance> instances = instanceDao.getAllInstances();
		Map<String,String> instanceMap = new HashMap<String,String>();
		instances.forEach( i -> instanceMap.put(i.getInstanceID(), i.getInstanceName()));
		mav.addObject("instances",instanceMap);
		
		//create List of events and add it to the model
		List<Event> events = new ArrayList<Event>();
		eventDao.getAllEvents().forEach( i -> events.add(i));
		mav.addObject("events",events);
		
		return mav;
	}
	
	@RequestMapping(value="/eventPage/{id}", method=RequestMethod.GET) 
	public ModelAndView displayEventPage(HttpSession session, @PathVariable("id") String id) {
		ModelAndView mav = new ModelAndView("event");
		EventDAO dao = new EventDAO();
		mav.addObject("event", dao.getEventByID(id));
		
		mav.addObject("message", session.getAttribute("message")); //add error message to model if it exists
		session.removeAttribute("message"); //remove message from session
		
		return mav;
	}
	
	@RequestMapping(value="/eventPage/{id}", method=RequestMethod.POST)
	public void signUpToEvent(HttpSession session, HttpServletResponse response, @PathVariable("id") String id) throws IOException {
		AttendingDAO attendingDao = new AttendingDAO();
		
		//if there is no active session, display the login page
		if (session.getAttribute("user") == null) { 
			session.setAttribute("message", "You must be logged in to sign up to an event."); //add error message to session
			response.sendRedirect("../eventPage/" + id); //reload same page
			return ;
		}
		
		String eventID = id;
		String userID =  ((User)session.getAttribute("user") ).getUserID();
		
		if(attendingDao.signUp(eventID, userID)) { //if user was successfully signed up,
			response.sendRedirect("../home"); //go to the home page
		} else { //user could not be signed up (most likely because they are already signed up)
			session.setAttribute("message", "Could not sign up to event."); //add error message to session
			response.sendRedirect("../eventPage/" + id); //reload same page
		}

		return ;
	}
}
