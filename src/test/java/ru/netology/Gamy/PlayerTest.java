package ru.netology.Gamy;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.HashMap;

public class PlayerTest {
    GameStore store = new GameStore();
    Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
    Game game2 = store.publishGame("Контра", "Шутеры");
    Game game3 = store.publishGame("Вальхельм", "Выживастик");
    Game game4 = store.publishGame("Герои меча и магии 3", "Стратегия");
    Game game5 = store.publishGame("Кот в сапогах", "Спорт");
    Game game6 = store.publishGame("Логика", "Програмирование");
    Game game7 = store.publishGame("Космос", "Стратегия");
    Game game8 = store.publishGame("Раст", "Выживастик");
    Game game9 = store.publishGame("Хутор", "Стратегия");
    Player player = new Player("Петя");



    // блок Set/get Name
    @Test
    public void shouldName() {
        player.setName("Петя");

        String expected = "Петя";
        String actual = player.getName();

        assertEquals(expected, actual);
    }

    // блок set/get Map лист
    @Test
    public void shouldHashMap() {
        HashMap<Game, Integer> tmp = new HashMap<>();
        tmp.put(game1, 3);
        player.setPlayedTime(tmp);

        HashMap<Game, Integer> actual = player.getPlayedTime();
        HashMap<Game, Integer> expected = tmp;

        Assertions.assertEquals(expected, actual);
    }


    @Test
    public void shouldCheckPlayOnStore() {
        player.installGame(game3);
        player.play(game3, 3);
        assertThrows(RuntimeException.class, () -> player.play(game1, 5));
    }

    @Test
    public void shouldSumGenreIfOneGame() {
        player.installGame(game1);
        player.play(game1, 3);

        int expected = 3;
        int actual = player.sumGenre(game1.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfZeroGame2() {
        player.installGame(game3);
        player.installGame(game8);
        player.play(game3, 0);
        player.play(game8, 0);
        int expected = 0;
        int actual = player.sumGenre(game8.getGenre());
        assertEquals(expected, actual);
    }
    @Test
    public void shouldSumGenreIfZeroGame() {
        player.installGame(game2);
        int expected = 0;
        int actual = player.sumGenre(game2.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfTwoGame() {
        player.installGame(game4);

        player.installGame(game7);

        player.play(game4, 3);

        player.play(game7, 5);

        int expected = 8;

        int actual = player.sumGenre(game4.getGenre());

        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfOneGameTwice() {
        player.installGame(game4);

        player.installGame(game4);

        player.play(game4, 1);

        player.play(game4, 4);

        int expected = 5;

        int actual = player.sumGenre(game4.getGenre());

        assertEquals(expected, actual);
    }


    @Test

    public void mostPlayerByGenreOneGame() {

        player.installGame(game4);

        player.play(game4, 5);

        String searchGenre = "Стратегия";

        String expected = "Герои меча и магии 3";

        String actual = player.mostPlayerByGenre(searchGenre);

        assertEquals(expected, actual);

    }

    @Test

    public void mostPlayerByGenreTwoGame() {
        player.installGame(game7);

        player.installGame(game4);

        player.play(game7, 7);

        player.play(game4, 2);

        String searchGenre = "Стратегия";

        String expected = "Космос";

        String actual = player.mostPlayerByGenre(searchGenre);

        assertEquals(expected, actual);

    }

    @Test
    public void mostPlayerByGenreMaxGame() {
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.installGame(game4);
        player.installGame(game5);
        player.installGame(game6);
        player.installGame(game7);
        player.installGame(game8);
        player.installGame(game9);

        player.play(game1, 1);
        player.play(game2, 5);
        player.play(game3, 6);
        player.play(game4, 3);
        player.play(game5, 5);
        player.play(game6, 11);
        player.play(game7, 20);
        player.play(game8, 11);
        player.play(game9, 21);

        String searchGenre = "Стратегия";

        String expected = "Хутор";
        String actual = player.mostPlayerByGenre(searchGenre);
        assertEquals(expected, actual);

    }
    @Test
    public void mostPlayerByGenreNull() {
        player.installGame(game7);

        player.installGame(game4);

        player.play(game7, -1);


        String searchGenre = "Стратегия";

        String expected = null;

        String actual = player.mostPlayerByGenre(searchGenre);

        assertEquals(expected, actual);
    }
}

