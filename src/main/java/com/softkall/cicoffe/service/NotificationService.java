package com.softkall.cicoffe.service;


import com.softkall.cicoffe.model.entity.Member;

import java.util.Collection;

/**
 * @author Nidhal Dogga
 * @created 11/14/2020 11:52 PM
 * SoftKall™ All rights reserved.
 */


public interface NotificationService {

  void sendNotification(Collection<Member> members, String heading, String message);

  public enum Channel {
    PUSH_NOTIFICATION,
    EMAIL,
    SMS
  }

}
