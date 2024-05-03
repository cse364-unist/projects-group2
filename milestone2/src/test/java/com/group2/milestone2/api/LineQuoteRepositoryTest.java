package com.group2.milestone2.api;

import com.group2.milestone2.config.TestJpaConfig;
import com.group2.milestone2.domain.line_quote.domain.LineQuote;
import com.group2.milestone2.domain.line_quote.repository.LineQuoteRepository;
import com.group2.milestone2.domain.user.domain.TheUser;
import com.group2.milestone2.domain.user.repository.TheUserRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(TestJpaConfig.class)
public class LineQuoteRepositoryTest {

    @Autowired
    private TheUserRepository theUserRepository;
    @Autowired
    private LineQuoteRepository lineQuoteRepository;

    @Test
    void findLineQuoteByQueryNormal(){
        TheUser theUser = TheUser.create("normalId", "normalPW");
        theUser.setFavoriteLines(List.of());
        theUserRepository.save(theUser);

        List<LineQuote> foundLineQuote = lineQuoteRepository.findLineQuoteByQuery(null, true, theUser);

        Assertions.assertThat(foundLineQuote).isEqualTo(List.of());
    }

    @Test
    void findLineQuoteByQueryWithTag(){
        TheUser theUser = TheUser.create("normalId", "normalPW");
        theUser.setFavoriteLines(List.of());
        theUserRepository.save(theUser);

        List<LineQuote> foundLineQuote = lineQuoteRepository.findLineQuoteByQuery(List.of("sad"), null, theUser);

        Assertions.assertThat(foundLineQuote).isEqualTo(List.of());
    }

    @Test
    void findLineQuoteByQueryFalseFavorite(){
        TheUser theUser = TheUser.create("normalId", "normalPW");
        theUser.setFavoriteLines(List.of());
        theUserRepository.save(theUser);

        List<LineQuote> foundLineQuote = lineQuoteRepository.findLineQuoteByQuery(List.of("sad"), false, theUser);

        Assertions.assertThat(foundLineQuote).isEqualTo(List.of());
    }
}
