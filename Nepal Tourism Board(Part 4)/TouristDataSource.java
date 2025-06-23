import java.util.List;

public abstract class TouristDataSource {
    private String sourceName;

    public TouristDataSource(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getSourceName() {
        return sourceName;
    }

    public abstract List<String> fetchData() throws DataSourceAccessException;
}
