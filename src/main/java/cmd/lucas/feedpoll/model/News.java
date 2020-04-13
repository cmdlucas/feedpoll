package cmd.lucas.feedpoll.model;

import java.math.BigInteger;
import java.time.LocalDate;

public class News {
    public BigInteger id;
    public long timestamp;
    public LocalDate storageDate;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public LocalDate getStorageDate() {
        return storageDate;
    }

    public void setStorageDate(LocalDate storageDate) {
        this.storageDate = storageDate;
    }
}
