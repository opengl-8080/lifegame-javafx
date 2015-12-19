# lifegame-javafx
Life Game by JavaFX

```bash
$ ./gradlew build

$ cd build/libs

$ java -jar lifegame.jar --id=1
```

|id|description|
|---|---|
|1|Duke|
|2|Oscillator|
|3|space ship|
|4|glider gun|
|5|Duke2 (**default**)|

gradle でビルドして、 `lifegame.jar` を普通に jar コマンドで起動してください。
そのとき、引数（`--id=<id>`）で実行したいゲーム定義を指定します。

デフォルトは `5` の `Duke2` です。
