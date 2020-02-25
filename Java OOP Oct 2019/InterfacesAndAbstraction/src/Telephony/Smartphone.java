package Telephony;

import java.util.List;

public class Smartphone implements Callable, Browsable {
    private List<String> numbers;
    private List<String> urls;

    public Smartphone(List<String> numbers, List<String> urls) {
        this.numbers = numbers;
        this.urls = urls;
    }

    private boolean validateNumber(String number){
        for (int i = 0; i < number.length(); i++) {
            if(!Character.isDigit(number.charAt(i))){
                return false;
            }
        }
        return true;
    }

    private boolean validateURLs(String url){
        for (int i = 0; i < url.length(); i++) {
            if(Character.isDigit(url.charAt(i))){
                return false;
            }
        }
        return true;
    }


    @Override
    public String browse() {
        StringBuilder sb = new StringBuilder();
        for (String url : urls) {
            if(validateURLs(url)){
                sb.append("Browsing: ").append(url).append("!").append(System.lineSeparator());
            }else
                sb.append("Invalid URL!").append(System.lineSeparator());
        }
        return sb.toString();
    }

    @Override
    public String call() {
        StringBuilder sb = new StringBuilder();
        for (String number : numbers) {
            if(validateNumber(number)){
                sb.append("Calling... ").append(number).append(System.lineSeparator());
            }else
                sb.append("Invalid number!").append(System.lineSeparator());
        }
        return sb.toString();
    }
}
