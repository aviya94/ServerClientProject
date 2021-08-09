package PartOneProject;

import org.jetbrains.annotations.NotNull;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public final class MyUUID implements Comparable<MyUUID> {
    private final UUID uuid;
    private String key;

    public MyUUID(UUID uniqueID) {
        this.uuid = uniqueID;
    }

    public MyUUID(String Key){
        this.key=Key;
        uuid =Encoder(Key);
    }


    public static UUID Encoder(@NotNull final String key){
        UUID uuid = UUID.nameUUIDFromBytes(key.getBytes(StandardCharsets.UTF_8));
        return uuid;
    }

    public static String Decoder() {
        return Decoder();
    }

    public static String Decoder(@NotNull final MyUUID myUUID){
        return myUUID.key;
    }
    public String getStringUUID(){
        return this.key;
    }

    public boolean equals(MyUUID b){
        return this.uuid.equals(b.uuid);
    }

    @Override
    public  String toString(){
        return key;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public int compareTo(@NotNull MyUUID o) {
        return this.uuid.compareTo(o.uuid);
    }
}
