package cz.businesslogic.eshop.controller;

import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.IntStream;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableScheduling
@RestController
@RequestMapping("/email")
public class Email2Controller {

	private Queue<Integer> queue = new LinkedList<>();
	
	@GetMapping("/sendMails2")
	public void sendEmails() {
		IntStream.range(0,  10)
		.forEach(queue::add);
	}
	
	@Scheduled(fixedDelay=5000)
	private void schedule() {
		while (!queue.isEmpty()) {
			sendMail(queue.poll());
		}
	}
	
	@SneakyThrows
	private void sendMail(int i) {
		Thread.sleep(1_000);
		log.info("sending email {} ...", i);
	}
}
