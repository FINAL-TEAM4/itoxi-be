package com.itoxi.petnuri.domain.petTalk.dto.response;

import com.itoxi.petnuri.domain.petTalk.entity.PetTalk;
import java.util.List;
import java.util.stream.Collectors;

import com.itoxi.petnuri.domain.petTalk.entity.PetTalkView;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
@Builder
public class LoadPetTalkPostsResponse {

    private int currentPage;
    private int totalPages;
    private long totalElements;

    private List<LoadPetTalkPreviewPostResponse> petTalkPosts;

    public static LoadPetTalkPostsResponse fromPage(Page<PetTalkView> page) {
        List<LoadPetTalkPreviewPostResponse> loadPetTalkPreviewPostResponses =
                page.getContent()
                        .stream()
                        .map(LoadPetTalkPreviewPostResponse::fromEntity)
                        .collect(Collectors.toList());

        return LoadPetTalkPostsResponse.builder()
                .currentPage(page.getNumber())
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .petTalkPosts(loadPetTalkPreviewPostResponses)
                .build();
    }
}
