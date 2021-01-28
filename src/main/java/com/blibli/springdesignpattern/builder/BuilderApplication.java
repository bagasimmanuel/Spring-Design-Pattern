package com.blibli.springdesignpattern.builder;

import lombok.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class BuilderApplication {

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Mahasiswa {

        private String nama;
        private String nim;
        private String alamat;
        private Date tanggalLahir;

       // @Singular // Supaya bisa nggak perlu Array, tapi bisa .hobi.hobi.hobi bukan .hobi(Array.asList("Halo","Makan"))
        private List<String> hobi;
    }

    public static void main(String[] args) {
        Mahasiswa mahasiswa1 = new Mahasiswa();
        mahasiswa1.setNama("Bagas");
        mahasiswa1.setNim("123");
        mahasiswa1.setAlamat("Indonesia");
        mahasiswa1.setTanggalLahir(new Date());
        mahasiswa1.setHobi(Arrays.asList("Game","Tidur"));
        // Daripada kamu buat setting dan getter satu satu, terus km buat .setNama, .setNim dll, bisa pakai Builder
        // Kalau pakai builder akan return Mahasiswa builder dimana dia ntar bisa membuatkan yang .builder.nama().nim().alamat.().build();

        Mahasiswa mahasiswa2 = Mahasiswa.builder().nama("Bagas").nim("123").alamat("Duar").tanggalLahir(new Date()).hobi(Arrays.asList("Makan","Tidur").build();
        System.out.println(mahasiswa2);
    }



}
