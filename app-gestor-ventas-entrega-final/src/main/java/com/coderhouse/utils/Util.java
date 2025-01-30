package com.coderhouse.utils;

import com.coderhouse.dtos.TimeResponseDTO;

import java.time.LocalDateTime;

public class Util {

    public static LocalDateTime convertirFecha(TimeResponseDTO fechaDTO) {
        return LocalDateTime.of(
                fechaDTO.getYear(),
                fechaDTO.getMonth(),
                fechaDTO.getDay(),
                Integer.parseInt(fechaDTO.getTime().split(":")[0]), // Hora
                Integer.parseInt(fechaDTO.getTime().split(":")[1])  // Minutos
        );
    }


}
