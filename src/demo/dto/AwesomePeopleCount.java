package demo.dto;

public class AwesomePeopleCount {
    //region Fields
    private boolean awesome;
    private long count;
    //endregion

    //region Constructors
    public AwesomePeopleCount(boolean awesome, long count) {
        this.awesome = awesome;
        this.count = count;
    }

    public AwesomePeopleCount() {

    }
    //endregion

    //region Props
    public boolean isAwesome() {
        return awesome;
    }

    public void setAwesome(boolean awesome) {
        this.awesome = awesome;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
    //endregion
}
