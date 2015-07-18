package logbook.internal;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import logbook.util.builder.Builders;

/**
 * 経験値テーブル
 *
 */
public class ExpTable {

    /**
     * 経験値テーブルプリセット値
     */
    private static final Map<Integer, Long> EXP_TABLE = Builders.newMapBuilder(LinkedHashMap<Integer, Long>::new)
            .put(1, 0l)
            .put(2, 100l)
            .put(3, 300l)
            .put(4, 600l)
            .put(5, 1000l)
            .put(6, 1500l)
            .put(7, 2100l)
            .put(8, 2800l)
            .put(9, 3600l)
            .put(10, 4500l)
            .put(11, 5500l)
            .put(12, 6600l)
            .put(13, 7800l)
            .put(14, 9100l)
            .put(15, 10500l)
            .put(16, 12000l)
            .put(17, 13600l)
            .put(18, 15300l)
            .put(19, 17100l)
            .put(20, 19000l)
            .put(21, 21000l)
            .put(22, 23100l)
            .put(23, 25300l)
            .put(24, 27600l)
            .put(25, 30000l)
            .put(26, 32500l)
            .put(27, 35100l)
            .put(28, 37800l)
            .put(29, 40600l)
            .put(30, 43500l)
            .put(31, 46500l)
            .put(32, 49600l)
            .put(33, 52800l)
            .put(34, 56100l)
            .put(35, 59500l)
            .put(36, 63000l)
            .put(37, 66600l)
            .put(38, 70300l)
            .put(39, 74100l)
            .put(40, 78000l)
            .put(41, 82000l)
            .put(42, 86100l)
            .put(43, 90300l)
            .put(44, 94600l)
            .put(45, 99000l)
            .put(46, 103500l)
            .put(47, 108100l)
            .put(48, 112800l)
            .put(49, 117600l)
            .put(50, 122500l)
            .put(51, 127500l)
            .put(52, 132700l)
            .put(53, 138100l)
            .put(54, 143700l)
            .put(55, 149500l)
            .put(56, 155500l)
            .put(57, 161700l)
            .put(58, 168100l)
            .put(59, 174700l)
            .put(60, 181500l)
            .put(61, 188500l)
            .put(62, 195800l)
            .put(63, 203400l)
            .put(64, 211300l)
            .put(65, 219500l)
            .put(66, 228000l)
            .put(67, 236800l)
            .put(68, 245900l)
            .put(69, 255300l)
            .put(70, 265000l)
            .put(71, 275000l)
            .put(72, 285400l)
            .put(73, 296200l)
            .put(74, 307400l)
            .put(75, 319000l)
            .put(76, 331000l)
            .put(77, 343400l)
            .put(78, 356200l)
            .put(79, 369400l)
            .put(80, 383000l)
            .put(81, 397000l)
            .put(82, 411500l)
            .put(83, 426500l)
            .put(84, 442000l)
            .put(85, 458000l)
            .put(86, 474500l)
            .put(87, 491500l)
            .put(88, 509000l)
            .put(89, 527000l)
            .put(90, 545500l)
            .put(91, 564500l)
            .put(92, 584500l)
            .put(93, 606500l)
            .put(94, 631500l)
            .put(95, 661500l)
            .put(96, 701500l)
            .put(97, 761500l)
            .put(98, 851500l)
            .put(99, 1000000l)
            .put(100, 1000000l)
            .put(101, 1010000l)
            .put(102, 1011000l)
            .put(103, 1013000l)
            .put(104, 1016000l)
            .put(105, 1020000l)
            .put(106, 1025000l)
            .put(107, 1031000l)
            .put(108, 1038000l)
            .put(109, 1046000l)
            .put(110, 1055000l)
            .put(111, 1065000l)
            .put(112, 1077000l)
            .put(113, 1091000l)
            .put(114, 1107000l)
            .put(115, 1125000l)
            .put(116, 1145000l)
            .put(117, 1168000l)
            .put(118, 1194000l)
            .put(119, 1223000l)
            .put(120, 1255000l)
            .put(121, 1290000l)
            .put(122, 1329000l)
            .put(123, 1372000l)
            .put(124, 1419000l)
            .put(125, 1470000l)
            .put(126, 1525000l)
            .put(127, 1584000l)
            .put(128, 1647000l)
            .put(129, 1714000l)
            .put(130, 1785000l)
            .put(131, 1860000l)
            .put(132, 1940000l)
            .put(133, 2025000l)
            .put(134, 2115000l)
            .put(135, 2210000l)
            .put(136, 2310000l)
            .put(137, 2415000l)
            .put(138, 2525000l)
            .put(139, 2640000l)
            .put(140, 2760000l)
            .put(141, 2887000l)
            .put(142, 3021000l)
            .put(143, 3162000l)
            .put(144, 3310000l)
            .put(145, 3465000l)
            .put(146, 3628000l)
            .put(147, 3799000l)
            .put(148, 3978000l)
            .put(149, 4165000l)
            .put(150, 4360000l)
            .build();

    /**
     * 経験値テーブルを取得します
     *
     * @return
     */
    public static Map<Integer, Long> get() {
        return Collections.unmodifiableMap(EXP_TABLE);
    }
}
