package gsm.gistory.domain.rank.service;

import gsm.gistory.domain.profile.entity.Profile;
import gsm.gistory.domain.profile.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Service
public class ProfileRankService {

    private final ProfileRepository profileRepository;

    public List<RankResponse> getTop5Subscribers() {
        Pageable top5 = PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "subCount"));
        List<Profile> top5Profiles = profileRepository.findTop5BySubCount(top5);

        return IntStream.range(0, top5Profiles.size())
                .mapToObj(i -> new RankResponse(
                        i + 1,
                        top5Profiles.get(i).getName(),
                        top5Profiles.get(i).getSubCount()
                ))
                .collect(Collectors.toList());
    }

    public record RankResponse(int rank, String name, Long subCount) {}
}

