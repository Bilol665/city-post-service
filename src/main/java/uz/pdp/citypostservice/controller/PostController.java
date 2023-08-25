package uz.pdp.citypostservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.citypostservice.domain.dto.PostCreateDto;
import uz.pdp.citypostservice.domain.dto.response.ApiResponse;
import uz.pdp.citypostservice.service.post.PostService;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post/api/v1/")
public class PostController {
    private final PostService postService;
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> savePost(
            @RequestBody PostCreateDto postCreateDto,
            Principal principal
    ) {
        return ResponseEntity.ok(postService.save(postCreateDto,principal));
    }
    @GetMapping("/{id}/get")
    public ResponseEntity<ApiResponse> get(
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(postService.getById(id));
    }
    @GetMapping("/get")
    public ResponseEntity<ApiResponse> get(
            Principal principal
    ) {
        return ResponseEntity.ok(postService.get(principal));
    }
    @PutMapping("/{id}/update")
    public ResponseEntity<ApiResponse> update(
            @RequestBody PostCreateDto postCreateDto,
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(postService.update(postCreateDto,id));
    }
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<ApiResponse> delete(
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(postService.delete(id));
    }
}
