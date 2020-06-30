package ru.spring.artproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.spring.artproject.domain.Currency;
import ru.spring.artproject.repository.CurrencyRepository;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CurrencyService {
    @Autowired
    CurrencyRepository repository;

    public List<Currency> findAllCurrency() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public void saveCurrency(Currency currency) {
        repository.save(currency);
    }

    public Currency getCurrencyById(Integer id) {
        return repository.findById(id).get();
    }

    public void getCurrencies() {

        try {
            URL url = new URL("http://www.cbr.ru/scripts/XML_daily.asp");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(url.openStream());
            NodeList valuteElement = document.getDocumentElement().getElementsByTagName("Valute");
            Date fileDate = new SimpleDateFormat("dd.MM.yyyy").parse(document.getDocumentElement().getAttribute("Date"));
            Timestamp timestamp1 = new java.sql.Timestamp(fileDate.getTime());
            boolean present = repository.findById(1).isPresent();
            Date myDate;
            if (!present) {
                myDate = new Date(1L);
            } else {
                myDate = repository.findById(1).get().getDate();
            }
            Timestamp timestamp2 = new java.sql.Timestamp(myDate.getTime());
            if (!timestamp1.equals(timestamp2)) {
                for (int i = 0; i < valuteElement.getLength(); i++) {
                    Node valute = valuteElement.item(i);
                    Currency curr = new Currency();
                    curr.setId(i + 1);
                    curr.setNumcode(valute.getChildNodes().item(0).getTextContent());
                    curr.setCharCode(valute.getChildNodes().item(1).getTextContent());
                    curr.setNominal(Integer.parseInt(valute.getChildNodes().item(2).getTextContent(),10));
                    curr.setName(valute.getChildNodes().item(3).getTextContent());
                    curr.setValue(Double.parseDouble(valute.getChildNodes().item(4).getTextContent().replace(',','.')));
                    curr.setDate(new SimpleDateFormat("dd.MM.yyyy").parse(document.getDocumentElement().getAttribute("Date")));
                    repository.save(curr);
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException | ParseException e) {
            System.out.println("Exception :" + e);
        }

    }
}
