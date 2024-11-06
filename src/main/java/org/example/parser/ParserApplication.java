package org.example.parser;

import org.example.parser.dto.PartyIdentifierDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
public class ParserApplication implements CommandLineRunner {

    private final XMLParsingService<PartyIdentifierDTO> xmlParserService;

    @Autowired
    public ParserApplication(XMLParsingService<PartyIdentifierDTO> xmlParserService) {
        this.xmlParserService = xmlParserService;
    }

    public static void main(String[] args) {
        SpringApplication.run(ParserApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            // Определяем путь к файлу app.xml в корне проекта
            Path filePath = Paths.get("app5.xml"); // EPGU
//            Path filePath = Paths.get("ApplicantBag_OnRp_zayavitel.xml"); // Лк ОНРП
//            Path filePath = Paths.get("ApplicantBag1.xml"); // Лк ОНРП
//            Path filePath = Paths.get("RepresentativeBag_OnRp_predstavitel.xml"); // Лк ОНРП


            // Проверяем, существует ли файл
            File file = filePath.toFile();
            if (!file.exists()) {
                System.out.println("Файл app.xml не найден в корне проекта");
                return;
            }

            // Чтение файла в массив байт
            byte[] fileBytes = Files.readAllBytes(filePath);

            // Вывод информации о количестве байт для проверки
            System.out.println("Файл успешно прочитан, количество байт: " + fileBytes.length);

            // Используем xmlParserService для извлечения данных
            PartyIdentifierDTO dto = xmlParserService.extractData(fileBytes, PartyIdentifierDTO.class);
//            System.out.println(dto);

        } catch (IOException e) {
            // Обработка ошибки, если возникли проблемы с чтением файла
            e.printStackTrace();
        }
    }
}
