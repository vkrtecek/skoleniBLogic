package cz.businesslogic.eshop.controller;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/email")
public class EmailController {

	@Async
	@GetMapping("/sendMails")
	public void sendEmails() {
		for (int i = 0; i < 0xa; i++)
			sendMail(1_000, i);
	}
	
	@SneakyThrows
	private void sendMail(int ms, int id) {
		Thread.sleep(ms);
		log.info("sending {} email ... ... ...", id);
	}
}
