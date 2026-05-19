package com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.utils;

import java.util.Arrays;
import java.util.stream.IntStream;
import com.simplecore.erp.gui.workspace.LyraWorkspace;

public class CharacteristicsOptionsData {

    /* 
C_MAT	Características para materiales
C_STEQ	Características para equipos estacionarios
C_EMV	Características para equipos móviles
C_PPRD	Características de procesos
C_CCPR	Características de control de calidad
C_CHR	Características de recursos humanos
C_GENS	Características generales
C_SPC	Características especiales 
     */

    private static final String[] charGroups = {
        "C_MAT",
        "C_STEQ",
        "C_EMV",
        "C_PPRD",
        "C_CCPR",
        "C_CHR",
        "C_GENS",
        "C_SPC"};

    public static String[] getCharGroupDescriptions() {
        String[] keys = {
            "C_MAT.text",
            "C_STEQ.text",
            "C_EMV.text",
            "C_PPRD.text",
            "C_CCPR.text",
            "C_CHR.text",
            "C_GENS.text",
            "C_SPC.text"
        };
        return Arrays.stream(keys)
                .map(LyraWorkspace.getTranslator()::getTranslation)
                .toArray(String[]::new);
    }
    public static String getCharGroupDescriptionByCode(String charCode) {
        return LyraWorkspace.getTranslator().getTranslation(charCode + ".text");
    }
    
    public static String getCharGroupCode(int index){
        return charGroups[index];
    }
    
    private static final String[] charStatus = {
        "IN_PREP",
        "LOCK",
        "RELEAS",};

    public static String[] getStatusDescriptions() {
        String[] keys = {
            "IN_PREP.text",
            "LOCK.text",
            "RELEAS.text"
        };
        return Arrays.stream(keys)
                .map(LyraWorkspace.getTranslator()::getTranslation)
                .toArray(String[]::new);
    }

    public static int getCharStatusIndex(String code) {
        return IntStream.range(0, charStatus.length)
                .filter(i -> code.equals(charStatus[i]))
                .findFirst()
                .orElse(-1);
    }

    public static String getCharStatusCode(int index) {
        return charStatus[index];
    }

    private static final String[] charFormats = {
        "CHAR",
        "CURR",
        "DATE",
        "NUM"};
    
    public static String[] getFormatDescriptions() {
        String[] keys = {
            "CHAR.text",
            "CURR.text",
            "DATE.text",
            "NUM.text"
        };
        return Arrays.stream(keys)
                .map(LyraWorkspace.getTranslator()::getTranslation)
                .toArray(String[]::new);
    }

    public static int getCharFormatIndex(String code) {
        return IntStream.range(0, charFormats.length)
                .filter(index -> code.equals(charFormats[index]))
                .findFirst()
                .orElse(-1);
    }

    public static String getCharFormatsCode(int index) {
        return charFormats[index];
    }

}
