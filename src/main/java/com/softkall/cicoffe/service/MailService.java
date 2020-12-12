package com.softkall.cicoffe.service;

import com.softkall.cicoffe.model.entity.Member;
import com.softkall.cicoffe.model.entity.Team;

/**
 * @author Nidhal Dogga
 * @created 12/12/2020 02:56
 * @credits SoftKall
 */


public interface MailService {
  void sendTeamInvitation(Team team, String email, Member member);
}
