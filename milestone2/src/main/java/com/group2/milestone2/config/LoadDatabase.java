package com.group2.milestone2.config;

import com.group2.milestone2.common.DateHandler;
import com.group2.milestone2.domain.line_quote.domain.LineQuote;
import com.group2.milestone2.domain.line_quote.repository.LineQuoteRepository;
import com.group2.milestone2.domain.line_tag.domain.LineTag;
import com.group2.milestone2.domain.line_tag.repository.LineTagRepository;
import com.group2.milestone2.domain.movie.domain.Movie;
import com.group2.milestone2.domain.movie.repository.MovieRepository;
import com.group2.milestone2.domain.user.repository.TheUserRepository;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(
        @Autowired LineTagRepository lineTagRepository,
        @Autowired LineQuoteRepository lineQuoteRepository,
        @Autowired MovieRepository movieRepository
    ) {
        List<List<String>> csvData = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/quote_sheet.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                csvData.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int main_data_start_row = 5;
        csvData.subList(0, main_data_start_row).clear();

        Movie movie = null;
        for (List<String> stringList : csvData) {
            if (!stringList.get(0).isEmpty()) {
                movie = Movie.create(
                    stringList.get(0),
                    stringList.get(1),
                    DateHandler.stringToLocalDate(stringList.get(2)),
                    stringList.get(6)
                );
                movieRepository.save(movie);
            }
            if (movie != null) {
                List<LineTag> lineTagList = parseAndSaveLineTag(stringList.get(5), lineTagRepository);
                LineQuote lineQuote = LineQuote.create(
                    stringList.get(3),
                    stringList.get(4),
                    movie,
                    lineTagList
                );
                lineQuoteRepository.save(lineQuote);
            }
        }

        return args -> {
            log.info("saving line quote date from the csv");
        };
    }

    private List<LineTag> parseAndSaveLineTag(String lineTagsString, LineTagRepository lineTagRepository) {
        String[] values = lineTagsString.split(",");
        List<LineTag> lineTagList = new ArrayList<>();
        for (String tagString : values
        ) {
            LineTag lineTag = lineTagRepository.findById(tagString)
                .orElse(lineTagRepository.save(LineTag.create(tagString)));
            lineTagList.add(lineTag);
        }
        return lineTagList;
    }
}