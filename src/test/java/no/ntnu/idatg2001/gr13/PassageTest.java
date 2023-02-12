package no.ntnu.idatg2001.gr13;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;

class PassageTest
{
    Passage passage;
    ArrayList<Object> arrayList = new ArrayList<>();
    PassageTest(){
    }

    @BeforeEach
    void setUp(){
        this.passage = new Passage("test title", "test content", arrayList);
    }
}
