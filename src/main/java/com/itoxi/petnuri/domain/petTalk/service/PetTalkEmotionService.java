package com.itoxi.petnuri.domain.petTalk.service;

import com.itoxi.petnuri.domain.member.entity.Member;
import com.itoxi.petnuri.domain.petTalk.dto.request.CreatePetTalkEmotionReq;
import com.itoxi.petnuri.domain.petTalk.entity.PetTalk;
import com.itoxi.petnuri.domain.petTalk.entity.PetTalkEmotion;
import com.itoxi.petnuri.domain.petTalk.repository.PetTalkEmotionRepository;
import com.itoxi.petnuri.domain.petTalk.repository.PetTalkRepository;
import com.itoxi.petnuri.domain.petTalk.type.EmojiType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PetTalkEmotionService {
    private final PetTalkRepository petTalkRepository;
    private final PetTalkEmotionRepository petTalkEmotionRepository;

    public void create(Member member, Long petTalkId, CreatePetTalkEmotionReq request) {
//        PetTalk petTalkPost = petTalkRepository.findById(petTalkId)
//                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시글입니다"));
        PetTalk petTalk = petTalkRepository.getById(petTalkId);

        // 중복 레코드 검사
        boolean isExists = petTalkEmotionRepository.existsByMemberAndPetTalkAndEmoji(member,
                petTalk, request.getEmoji());
        if (isExists) {
            throw new RuntimeException("이미 등록된 데이터입니다.");
        }

        PetTalkEmotion petTalkEmotion = PetTalkEmotion.create(member, petTalk, request.getEmoji());
        petTalkEmotionRepository.save(petTalkEmotion);
    }

    public void delete(Member member, Long petTalkId, EmojiType emoji) {
//        PetTalkPost petTalkPost = petTalkRepository.findById(petTalkId)
//                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시글입니다"));
        PetTalk petTalk = petTalkRepository.getById(petTalkId);

        PetTalkEmotion petTalkEmotion = petTalkEmotionRepository.findByMemberAndPetTalkAndEmoji(member,
                        petTalk, emoji)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 데이터입니다."));
        
        petTalkEmotionRepository.delete(petTalkEmotion);
    }
}