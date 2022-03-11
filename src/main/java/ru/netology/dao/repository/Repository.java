package ru.netology.dao.repository;

import org.springframework.core.io.ClassPathResource;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Repository
public class Repository {
    private final String query = read("/selectProducts.sql");

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public String getProductName(String name) {
        /*В методе обрабатываются ситуации, когда для одного имени возвращается больше одного товара*/
        final List<String> result = entityManager.createQuery(query)
                .setParameter("name", name)
                .getResultList();
        return result.isEmpty() ? "Nothing found" : String.join(", ", result);
    }

    @SuppressWarnings("SameParameterValue")
    private static String read(String scriptFileName) {
        try (final InputStream is = new ClassPathResource(scriptFileName).getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))) {
            return bufferedReader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}