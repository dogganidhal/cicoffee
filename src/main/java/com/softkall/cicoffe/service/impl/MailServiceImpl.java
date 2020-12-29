package com.softkall.cicoffe.service.impl;

import com.softkall.cicoffe.configuration.properties.MailConfigurationProperties;
import com.softkall.cicoffe.model.entity.Member;
import com.softkall.cicoffe.model.entity.Team;
import com.softkall.cicoffe.service.MailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Nidhal Dogga
 * @created 12/12/2020 02:57
 * SoftKall™ All rights reserved.
 */


@Slf4j
@Service
@AllArgsConstructor
public class MailServiceImpl implements MailService {

  private final JavaMailSender mailSender;
  private final TemplateEngine templateEngine;
  private final MailConfigurationProperties mailConfiguration;

  @Override
  public void sendTeamInvitation(Team team, String email, Member member, String link) {
    Map<String, Object> variables = new HashMap<>();
    variables.put("inviterFullName", String.format("%s %s", member.getFirstName(), member.getLastName()));
    variables.put("teamName", team.getName());
    variables.put("invitationUrl", link);

    String message = compileTemplate("team-invitation", variables);
    MimeMessagePreparator mimeMessage = mime -> {
      MimeMessageHelper messageHelper = new MimeMessageHelper(mime, "UTF-8");
      messageHelper.setFrom(mailConfiguration.getFrom());
      messageHelper.setTo(email);
      messageHelper.setSubject(String.format("Pausa Café ☕ : %s has invited you to join team %s", member.getFirstName(), team.getName()));
      messageHelper.setText(message, true);
    };
    try {
      mailSender.send(mimeMessage);
    } catch (MailException exception) {
      log.error(exception.getMessage(), exception);
    }
  }
  @Override
  public void sendResetPassword(String email, String link) {
    Map<String, Object> variables = new HashMap<>();
    variables.put("link", link);

    String message = compileTemplate("password-reset", variables);
    MimeMessagePreparator mimeMessage = mime -> {
      MimeMessageHelper messageHelper = new MimeMessageHelper(mime, "UTF-8");
      messageHelper.setFrom(mailConfiguration.getFrom());
      messageHelper.setTo(email);
      messageHelper.setSubject("Pausa Café ☕ : Reset your password ");
      messageHelper.setText(message, true);
    };
    try {
      mailSender.send(mimeMessage);
    } catch (MailException exception) {
      log.error(exception.getMessage(), exception);
    }
  }
  private String compileTemplate(String id, Map<String, Object> variables) {
    Context context = new Context();
    variables.forEach(context::setVariable);
    return templateEngine.process(id, context);
  }

}
