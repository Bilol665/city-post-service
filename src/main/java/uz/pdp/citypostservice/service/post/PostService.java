package uz.pdp.citypostservice.service.post;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.pdp.citypostservice.domain.dto.PostCreateDto;
import uz.pdp.citypostservice.domain.dto.UserReadDto;
import uz.pdp.citypostservice.domain.dto.response.ApiResponse;
import uz.pdp.citypostservice.domain.entity.post.PostEntity;
import uz.pdp.citypostservice.domain.entity.post.PostStatus;
import uz.pdp.citypostservice.exceptions.DataNotFoundException;
import uz.pdp.citypostservice.repository.post.PostRepository;
import uz.pdp.citypostservice.service.auth.AuthService;

import java.security.Principal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    private final AuthService authService;
    public ApiResponse save(PostCreateDto postCreateDto, Principal principal) {
        PostEntity post = modelMapper.map(postCreateDto, PostEntity.class);
        UserReadDto user = authService.getUser(principal.getName(), principal);
        post.setOwnerId(user.getId());
        post.setStatus(PostStatus.CREATED);
        PostEntity savedPost = postRepository.save(post);
        return new ApiResponse(HttpStatus.OK,true,"Successfully saved", savedPost);
    }

    public ApiResponse getById(UUID postId) {
        PostEntity postEntity = postRepository.findById(postId).orElseThrow(() -> new DataNotFoundException("Post not found!"));
        return new ApiResponse(HttpStatus.OK,true,"Success",postEntity);
    }

    public ApiResponse get(Principal principal) {
        UserReadDto user = authService.getUser(principal.getName(), principal);
        PostEntity postEntity = postRepository.findPostEntityByOwnerId(user.getId()).orElseThrow(() -> new DataNotFoundException("Post not found!"));
        return new ApiResponse(HttpStatus.OK,true,"Success",postEntity);
    }

    public ApiResponse update(PostCreateDto postCreateDto, UUID id) {
        PostEntity postEntity = postRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Post not found!"));
        modelMapper.map(postCreateDto,postEntity);
        postRepository.save(postEntity);
        return new ApiResponse(HttpStatus.OK,true,"Successfully updated!");
    }

    public ApiResponse delete(UUID id) {
        PostEntity postEntity = postRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Post not found!"));
        postRepository.delete(postEntity);
        return new ApiResponse(HttpStatus.OK,true,"Successfully deleted!");
    }
}
