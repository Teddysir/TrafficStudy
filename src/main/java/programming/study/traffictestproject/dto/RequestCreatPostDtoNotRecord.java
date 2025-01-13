package programming.study.traffictestproject.dto;

public class RequestCreatPostDtoNotRecord {

    private final String name;
    private final String content;

    public RequestCreatPostDtoNotRecord(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

}
