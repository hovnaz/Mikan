package am.mikan.mikan.mapper;

import am.mikan.mikan.dto.request.FeedbackRequest;
import am.mikan.mikan.dto.response.FeedbackResponse;
import am.mikan.mikan.mapper.base.BaseMapper;
import am.mikan.mikan.entity.Feedback;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedbackMapper implements BaseMapper<Feedback, FeedbackRequest, FeedbackResponse> {

    private final ModelMapper modelMapper;

    @Override
    public Feedback toEntity(FeedbackRequest feedbackRequest) {
        return modelMapper.map(feedbackRequest, Feedback.class);
    }

    @Override
    public FeedbackResponse toResponse(Feedback feedback) {
        return modelMapper.map(feedback, FeedbackResponse.class);
    }
}
