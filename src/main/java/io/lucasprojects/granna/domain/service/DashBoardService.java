package io.lucasprojects.granna.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.lucasprojects.granna.domain.Enum.TitleType;
import io.lucasprojects.granna.dto.DashBoard.DashBoardResponseDTO;
import io.lucasprojects.granna.dto.Title.TitleResponseDTO;

@Service
public class DashBoardService {

    @Autowired
    public TitleService titleService;

    public DashBoardResponseDTO getFlowCash(String start, String end) {

        Double totalToPay = 0.0;
        Double totalToReceive = 0.0;
        Double balance = 0.0;

        List<TitleResponseDTO> titlesToPay = new ArrayList<>();
        List<TitleResponseDTO> titlesToReceive = new ArrayList<>();

        List<TitleResponseDTO> list = titleService.getFlowCash(start, end);

        for(TitleResponseDTO titleResponse : list) {
            if(titleResponse.getTitleType() == TitleType.PAYABLE) {
                totalToPay += titleResponse.getValue();
                titlesToPay.add(titleResponse);
            } else {
                totalToReceive += titleResponse.getValue();   
                titlesToReceive.add(titleResponse);
            }
        }

        balance = totalToPay - totalToReceive;

        return new DashBoardResponseDTO(totalToPay, totalToReceive, balance, titlesToPay, titlesToReceive);
    }

}
