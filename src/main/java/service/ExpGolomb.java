package service;

import model.Source;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin Janicki on 2015-05-12.
 */
public class ExpGolomb {

    private Source source;
    private int index = 0;

    public ExpGolomb(Source source)
    {
        this.source = source;
    }

    public String encode()
    {
        int intToCode = source.getNextValue();
        //intToCode *= -1;
        System.out.println("Int to code: " + intToCode);
        //zamiana wartości ujemnych na dodatnie w celu zakodowania ich
        if(intToCode <= 0)
        {
            intToCode = -2 * intToCode + 1; //(-2 * intToCode) + 1 //(rozszerzenie dla ujemnych) + 1
        }
        else
        {
            intToCode = 2 * intToCode; //(2 * intToCode - 1) + 1 //(rozszerzenie dla ujemnych) + 1
        }

        //dodanie jedynki w celu rozpoczęcia kodu od słowa '1' dla zera
        String binary = Integer.toBinaryString(intToCode);
        //do prefiksu dopisywane będą zera
        StringBuilder prefix = new StringBuilder();
        for(int i = 0; i < binary.length() - 1; ++i)
        {
            prefix.append(0);
        }

        return prefix.append(binary).toString();
    }

    public List<Integer> decode(String codeWord)
    {
        List<Integer> encodedSymbols = new ArrayList<>();
        index = 0;
        while(index < codeWord.length())
        {
            encodedSymbols.add(decodeSymbol(codeWord));
        }
        return encodedSymbols;
    }

    private Integer decodeSymbol(String codeWord)
    {
        int prefixLength = 0;
        //znalezienie długości prefiksu pozwalające na określenie długości kodu dla symbolu
        while(index + prefixLength < codeWord.length() && codeWord.charAt(index + prefixLength) == '0')
        {
            prefixLength++;
        }

        String encodedSymbol = codeWord.substring(index + prefixLength, index + 2*prefixLength + 1);
        Integer decodedSymbol = Integer.parseInt(encodedSymbol, 2);

        if(decodedSymbol % 2 == 0)
        {
            decodedSymbol /= 2;
        }
        else
        {
            decodedSymbol = (decodedSymbol - 1) / -2;
        }

        //przesunięcie indeksu, żeby można było odkodować kolejne symbole
        index += 2*prefixLength + 1;

        return decodedSymbol;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }
}
