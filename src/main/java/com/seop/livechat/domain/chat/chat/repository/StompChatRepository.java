package com.seop.livechat.domain.chat.chat.repository;

import com.seop.livechat.domain.chat.chat.entity.StompChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StompChatRepository extends JpaRepository<StompChat, Long> {
}
