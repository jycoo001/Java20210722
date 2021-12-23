package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import entity.Reader;
import service.ReaderService;

@Controller
@RequestMapping("Reader")
public class ReaderController extends BasicController<Reader> {

	@Autowired
	ReaderService service;
}