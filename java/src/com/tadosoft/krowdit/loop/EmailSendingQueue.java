package com.tadosoft.krowdit.loop;

import java.util.LinkedList;

import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailSendingQueue extends Thread {
	private static final Logger log = LoggerFactory
			.getLogger(EmailSendingQueue.class);
	private static final String EmailName = "no-reply@krowdit.com";
	private static final String EmailPwd = "Krowdit2011";
	private static final String EmailServer = "smtpout.secureserver.net";
//	private static final String EmailName = "krowdit@163.com";
//	private static final String EmailPwd = "tadosoft";
//	private static final String EmailServer = "smtp.163.com";
	private static EmailSendingQueue instance;

	/** the email will a add to this queue and wait for sending later */
	private LinkedList<NotifyEmail> queue;
	protected boolean running = true;
	protected HtmlEmail emailSender;

	public synchronized static EmailSendingQueue getInstance() {
		if (instance == null) {
			instance = new EmailSendingQueue();
		}
		return instance;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public EmailSendingQueue() {
		super();
		queue = new LinkedList<NotifyEmail>();
		emailSender = new HtmlEmail();
	}

	/**
	 * add one email to the queue which will be sent one by one.
	 * 
	 * @param email
	 */
	public void addEmailToQueue(NotifyEmail email) {
		synchronized (queue) {
			queue.add(email);
			log.info("added " + email.getTo() + ", now size=" + queue.size());
			log.info("queue will be notified");
			queue.notify();
		}
	}
	
	/**
	 * 
	 * @return size of the queue
	 */
	public int size() {
		synchronized (queue) {
			return queue.size();
		}
	}

	/**
	 * main loop
	 */
	public void run() {
		while (running) {
			if (queue.size() == 0) {
				try {
					synchronized (queue) {
						log.info("queue is empty, thread will wait");
						queue.wait();
					}
				} catch (InterruptedException e) {
					// Ignore
				}
			}

			synchronized (queue) {
				try {
					NotifyEmail email = queue.remove();

					log.info("sending a mail to " + email.getTo());
					emailSender.setHostName(EmailServer);
					// emailSender.setSmtpPort(587);
					emailSender.setAuthentication(EmailName, EmailPwd);
					emailSender.setTLS(true);
					emailSender.setFrom(EmailName);
					emailSender.setCharset("UTF-8");
					emailSender.setSubject("Recover Password of Krowdit");
					emailSender.setHtmlMsg(email.getUserName()
							+ " ,Your password is: " + email.getPwd());
					emailSender.addTo(email.getTo());
					emailSender.send();
					Thread.sleep(3000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} // end of loop

		queue = null;
		emailSender = null;
	}

}
