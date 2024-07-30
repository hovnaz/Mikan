package am.mikan.mikan.service.impl;

import am.mikan.mikan.dto.request.FeedbackRequest;
import am.mikan.mikan.mapper.FeedbackMapper;
import am.mikan.mikan.entity.Feedback;
import am.mikan.mikan.repository.CommentRepository;
import am.mikan.mikan.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final CommentRepository commentRepository;
    private final FeedbackMapper feedbackMapper;

    @Override
    public void save(FeedbackRequest feedbackRequest) {
        Feedback feedback = feedbackMapper.toEntity(feedbackRequest);
        commentRepository.save(feedback);
    }
}
