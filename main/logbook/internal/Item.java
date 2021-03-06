package logbook.internal;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import logbook.dto.ItemDto;
import logbook.util.builder.Builders;

/**
 * アイテム
 *
 */
public class Item {

    /**
     * アイテムプリセット値
     */
    private static final Map<Integer, ItemDto> ITEM = Builders.newMapBuilder(ConcurrentHashMap<Integer, ItemDto>::new)
            .put(1, new ItemDto(1, 1, 1, 0, 0, 0, 1, 0, 0, 1, 0, "12cm単装砲", 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 1))
            .put(2, new ItemDto(2, 1, 1, 0, 0, 0, 2, 0, 0, 1, 0, "12.7cm連装砲", 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 2))
            .put(3, new ItemDto(3, 1, 16, 0, 0, 0, 2, 0, 0, 1, 0, "10cm連装高角砲", 0, 0, 0, 1,
                    0, 0, 0, 0, 0, 0, 7))
            .put(4, new ItemDto(4, 2, 2, 0, 0, 0, 2, 0, 1, 2, 0, "14cm単装砲", 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0))
            .put(5, new ItemDto(5, 2, 2, 0, 0, 0, 7, 0, 1, 2, 0, "15.5cm三連装砲", 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 4))
            .put(6, new ItemDto(6, 2, 2, 0, 0, 0, 8, 0, 0, 2, 0, "20.3cm連装砲", 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 3))
            .put(7, new ItemDto(7, 3, 3, 0, 0, 0, 15, 0, 0, 3, 0, "35.6cm連装砲", 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 4))
            .put(8, new ItemDto(8, 3, 3, 0, 0, 0, 20, 0, 0, 3, 0, "41cm連装砲", 0, 0, 0, 1,
                    0, 0, 0, 0, 0, 0, 4))
            .put(9, new ItemDto(9, 3, 3, 0, 0, 0, 26, 0, 0, 4, 0, "46cm三連装砲", 0, 0, 0, 2,
                    0, 0, 0, 0, 0, 0, 5))
            .put(10, new ItemDto(10, 4, 16, 0, 0, 0, 2, 0, 1, 1, 0, "12.7cm連装高角砲", 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 4))
            .put(11, new ItemDto(11, 4, 4, 0, 0, 0, 2, 0, 1, 2, 0, "15.2cm単装砲", 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0))
            .put(12, new ItemDto(12, 4, 4, 0, 0, 0, 7, 0, 2, 2, 0, "15.5cm三連装副砲", 0, 0, 0, 1,
                    0, 0, 0, 0, 0, 0, 3))
            .put(13, new ItemDto(13, 5, 5, 0, 0, 0, 0, 0, 0, 1, 0, "61cm三連装魚雷", 5, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0))
            .put(14, new ItemDto(14, 5, 5, 0, 0, 0, 0, 0, 0, 1, 0, "61cm四連装魚雷", 7, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0))
            .put(15, new ItemDto(15, 5, 5, 0, 0, 0, 0, 0, 0, 1, 0, "61cm四連装(酸素)魚雷", 10, 0, 0, 1,
                    0, 0, 0, 0, 0, 0, 0))
            .put(16, new ItemDto(16, 8, 8, 0, 0, 0, 0, 0, 0, 0, 0, "九七式艦攻", 5, 0, 16, 0,
                    0, 1, 0, 0, 0, 4, 0))
            .put(17, new ItemDto(17, 8, 8, 0, 0, 0, 0, 0, 0, 0, 0, "天山", 7, 0, 24, 1,
                    0, 1, 0, 0, 0, 3, 0))
            .put(18, new ItemDto(18, 8, 8, 0, 0, 0, 0, 0, 0, 0, 0, "流星", 10, 0, 56, 2,
                    0, 1, 0, 0, 0, 4, 0))
            .put(19, new ItemDto(19, 6, 6, 0, 0, 0, 0, 0, 0, 0, 0, "九六式艦戦", 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 2))
            .put(20, new ItemDto(20, 6, 6, 0, 0, 0, 0, 0, 0, 0, 0, "零式艦戦21型", 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 5))
            .put(21, new ItemDto(21, 6, 6, 0, 0, 0, 0, 0, 0, 0, 0, "零式艦戦52型", 0, 0, 0, 1,
                    0, 0, 0, 0, 0, 0, 6))
            .put(22, new ItemDto(22, 6, 6, 0, 0, 0, 0, 0, 0, 0, 0, "烈風", 0, 0, 0, 3,
                    0, 0, 0, 0, 0, 0, 10))
            .put(23, new ItemDto(23, 7, 7, 0, 0, 5, 0, 0, 0, 0, 0, "九九式艦爆", 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 3, 0))
            .put(24, new ItemDto(24, 7, 7, 0, 0, 8, 0, 0, 0, 0, 0, "彗星", 0, 0, 0, 1,
                    0, 0, 0, 0, 0, 3, 0))
            .put(25, new ItemDto(25, 10, 10, 0, 0, 1, 0, 0, 1, 0, 0, "零式水上偵察機", 0, 0, 10, 0,
                    0, 5, 0, 0, 0, 2, 1))
            .put(26, new ItemDto(26, 11, 10, 0, 0, 4, 0, 0, 1, 0, 0, "瑞雲", 0, 0, 13, 1,
                    0, 6, 0, 0, 0, 4, 2))
            .put(27, new ItemDto(27, 12, 11, 0, 0, 0, 0, 0, 1, 0, 0, "13号対空電探", 0, 0, 3, 1,
                    0, 3, 0, 0, 0, 0, 2))
            .put(28, new ItemDto(28, 12, 11, 0, 0, 0, 0, 0, 3, 0, 0, "22号対水上電探", 0, 0, 10, 1,
                    0, 5, 0, 0, 0, 0, 0))
            .put(29, new ItemDto(29, 12, 11, 0, 0, 0, 0, 0, 5, 0, 0, "33号対水上電探", 0, 0, 12, 2,
                    0, 7, 0, 0, 0, 0, 0))
            .put(30, new ItemDto(30, 13, 11, 0, 0, 0, 0, 0, 2, 0, 0, "21号対空電探", 0, 0, 3, 2,
                    0, 4, 0, 0, 0, 0, 4))
            .put(31, new ItemDto(31, 13, 11, 0, 0, 0, 0, 0, 8, 0, 0, "32号対水上電探", 0, 0, 15, 3,
                    0, 10, 0, 0, 0, 0, 0))
            .put(32, new ItemDto(32, 13, 11, 0, 0, 0, 0, 0, 4, 0, 0, "14号対空電探", 0, 0, 3, 4,
                    0, 5, 0, 0, 0, 0, 6))
            .put(33, new ItemDto(33, 17, 19, 0, 0, 0, 0, 6, 0, 0, 0, "改良型艦本式タービン", 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0))
            .put(34, new ItemDto(34, 17, 19, 0, 0, 0, 0, 10, 0, 0, 0, "強化型艦本式缶", 0, 0, 0, 1,
                    0, 0, 0, 0, 0, 0, 0))
            .put(35, new ItemDto(35, 18, 12, 0, 0, 0, 0, 0, 0, 0, 0, "三式弾", 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 5))
            .put(36, new ItemDto(36, 19, 13, 0, 0, 0, 8, 0, 1, 0, 0, "九一式徹甲弾", 0, 0, 0, 1,
                    0, 0, 0, 0, 0, 0, 0))
            .put(37, new ItemDto(37, 21, 15, 0, 0, 0, 0, 1, 0, 0, 0, "7.7mm機銃", 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 2))
            .put(38, new ItemDto(38, 21, 15, 0, 0, 0, 0, 1, 0, 0, 0, "12.7mm単装機銃", 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 3))
            .put(39, new ItemDto(39, 21, 15, 0, 0, 0, 0, 1, 0, 0, 0, "25mm連装機銃", 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 5))
            .put(40, new ItemDto(40, 21, 15, 0, 0, 0, 0, 1, 0, 0, 0, "25mm三連装機銃", 0, 0, 0, 1,
                    0, 0, 0, 0, 0, 0, 6))
            .put(41, new ItemDto(41, 22, 5, 0, 0, 0, 0, 0, 0, 0, 0, "甲標的", 12, 0, 0, 1,
                    0, 0, 0, 0, 0, 0, 0))
            .put(42, new ItemDto(42, 23, 14, 0, 0, 0, 0, 0, 0, 0, 0, "応急修理要員", 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0))
            .put(43, new ItemDto(43, 23, 14, 0, 0, 0, 0, 0, 0, 0, 0, "応急修理女神", 0, 0, 0, 2,
                    0, 0, 0, 0, 0, 0, 0))
            .put(44, new ItemDto(44, 15, 17, 0, 0, 0, 0, 0, 0, 0, 0, "九四式爆雷投射機", 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 5, 0))
            .put(45, new ItemDto(45, 15, 17, 0, 0, 0, 0, 0, 0, 0, 0, "三式爆雷投射機", 0, 0, 0, 2,
                    0, 0, 0, 0, 0, 8, 0))
            .put(46, new ItemDto(46, 14, 18, 0, 0, 0, 0, 0, 1, 0, 0, "九三式水中聴音機", 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 6, 0))
            .put(47, new ItemDto(47, 14, 18, 0, 0, 0, 0, 0, 2, 0, 0, "三式水中探信儀", 0, 0, 0, 2,
                    0, 0, 0, 0, 0, 10, 0))
            .put(48, new ItemDto(48, 1, 16, 0, 0, 0, 1, 0, 0, 1, 0, "12.7cm単装高角砲", 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 3))
            .put(49, new ItemDto(49, 21, 15, 0, 0, 0, 0, 1, 0, 0, 0, "25mm単装機銃", 0, 0, 0, 1,
                    0, 0, 0, 0, 0, 0, 4))
            .put(50, new ItemDto(50, 2, 2, 0, 0, 0, 10, 0, 0, 2, 0, "20.3cm(3号)連装砲", 0, 0, 0, 1,
                    0, 0, 0, 0, 0, 0, 4))
            .put(51, new ItemDto(51, 21, 15, 0, 0, 0, 0, 0, 0, 0, 0, "12cm30連装噴進砲", 0, 0, 0, 2,
                    0, 0, 0, 0, 0, 0, 8))
            .put(52, new ItemDto(52, 8, 8, 0, 0, 0, 0, 0, 0, 0, 0, "流星改", 13, 0, 0, 3,
                    0, 2, 0, 0, 0, 3, 0))
            .put(53, new ItemDto(53, 6, 6, 0, 0, 0, 0, 0, 0, 0, 0, "烈風改", 0, 0, 0, 4,
                    0, 0, 0, 0, 0, 0, 12))
            .put(54, new ItemDto(54, 9, 9, 0, 0, 0, 0, 0, 2, 0, 0, "彩雲", 0, 0, 0, 2,
                    0, 9, 0, 0, 0, 0, 0))
            .put(55, new ItemDto(55, 6, 6, 0, 0, 0, 0, 0, 0, 0, 0, "紫電改二", 0, 0, 0, 2,
                    0, 0, 0, 0, 0, 0, 9))
            .put(56, new ItemDto(56, 6, 6, 0, 0, 0, 0, 0, 0, 0, 0, "震電改", 0, 0, 0, 5,
                    0, 0, 0, 0, 0, 0, 15))
            .put(57, new ItemDto(57, 7, 7, 0, 0, 10, 0, 0, 0, 0, 0, "彗星一二型甲", 0, 0, 0, 2,
                    0, 1, 0, 0, 0, 3, 0))
            .put(58, new ItemDto(58, 5, 5, 0, 0, 0, 0, 0, 1, 1, 0, "61cm五連装(酸素)魚雷", 12, 0, 0, 2,
                    0, 0, 0, 0, 0, 0, 0))
            .put(59, new ItemDto(59, 10, 10, 0, 0, 1, 0, 0, 2, 0, 0, "零式水上観測機", 0, 0, 0, 1,
                    0, 6, 0, 0, 0, 4, 2))
            .put(60, new ItemDto(60, 7, 7, 0, 0, 4, 0, 0, 0, 0, 0, "零式艦戦62型(爆戦)", 0, 0, 0, 2,
                    0, 0, 0, 0, 0, 3, 4))
            .put(61, new ItemDto(61, 9, 9, 0, 0, 0, 0, 0, 3, 0, 0, "二式艦上偵察機", 0, 0, 0, 1,
                    0, 7, 0, 0, 0, 0, 1))
            .put(62, new ItemDto(62, 11, 10, 0, 0, 11, 0, 0, 1, 0, 0, "試製晴嵐", 0, 0, 0, 4,
                    0, 6, 0, 0, 0, 6, 0))
            .put(63, new ItemDto(63, 1, 1, 0, 0, 0, 3, 0, 0, 1, 0, "12.7cm連装砲B型改二", 0, 0, 0, 1,
                    0, 0, 0, 0, 0, 0, 2))
            .put(64, new ItemDto(64, 7, 7, 0, 0, 9, 0, 0, 1, 0, 0, "Ju87C改", 0, 0, 0, 3,
                    0, 0, 0, 0, 0, 5, 0))
            .put(65, new ItemDto(65, 2, 2, 0, 0, 0, 4, 0, 3, 2, 0, "15.2cm連装砲", 0, 0, 0, 2,
                    0, 0, 0, 0, 0, 0, 3))
            .put(66, new ItemDto(66, 4, 16, 0, 0, 0, 1, 0, 2, 1, 0, "8cm高角砲", 0, 0, 0, 3,
                    0, 0, 0, 0, 0, 0, 6))
            .put(67, new ItemDto(67, 5, 5, 0, 0, 0, 0, 0, 2, 1, 0, "53cm艦首(酸素)魚雷", 15, 0, 0, 1,
                    0, 0, 0, 0, 0, 0, 0))
            .put(68, new ItemDto(68, 24, 20, 0, 0, 0, 0, 0, 0, 0, 0, "大発動艇", 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0))
            .put(69, new ItemDto(69, 25, 21, 0, 0, 0, 0, 0, 1, 0, 0, "カ号観測機", 0, 0, 0, 3,
                    0, 0, 0, 0, 0, 9, 0))
            .put(70, new ItemDto(70, 26, 22, 0, 0, 0, 0, 0, 2, 0, 0, "三式指揮連絡機(対潜)", 0, 0, 0, 1,
                    0, 1, 0, 0, 0, 7, 0))
            .put(71, new ItemDto(71, 4, 16, 0, 0, 0, 1, 0, 1, 1, 0, "10cm連装高角砲(砲架)", 0, 0, 0, 3,
                    0, 0, 0, 0, 0, 0, 7))
            .put(72, new ItemDto(72, 27, 23, 0, 0, 0, 0, -2, 0, 0, 0, "増設バルジ(中型艦)", 0, 0, 0, 2,
                    0, 0, 0, 7, 0, 0, 0))
            .put(73, new ItemDto(73, 28, 23, 0, 0, 0, 0, -3, 0, 0, 0, "増設バルジ(大型艦)", 0, 0, 0, 2,
                    0, 0, 0, 9, 0, 0, 0))
            .put(74, new ItemDto(74, 29, 24, 0, 0, 0, 0, 0, 0, 0, 0, "探照灯", 0, 0, 0, 0,
                    0, 2, 0, 0, 0, 0, 0))
            .put(75, new ItemDto(75, 30, 25, 0, 0, 0, 0, 0, 0, 0, 0, "ドラム缶(輸送用)", 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0))
            .put(76, new ItemDto(76, 3, 3, 0, 0, 0, 16, 0, 1, 3, 0, "38cm連装砲", 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 1))
            .put(77, new ItemDto(77, 4, 4, 0, 0, 0, 4, 0, 2, 2, 0, "15cm連装副砲", 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 2))
            .put(78, new ItemDto(78, 1, 1, 0, 0, 0, 2, 0, 1, 1, 0, "12.7cm単装砲", 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0))
            .put(79, new ItemDto(79, 11, 10, 0, 0, 6, 0, 0, 1, 0, 0, "瑞雲(六三四空)", 0, 0, 0, 2,
                    0, 6, 0, 0, 0, 5, 2))
            .put(80, new ItemDto(80, 11, 10, 0, 0, 7, 0, 0, 1, 0, 0, "瑞雲12型", 0, 0, 0, 3,
                    0, 6, 0, 0, 0, 5, 3))
            .put(81, new ItemDto(81, 11, 10, 0, 0, 9, 0, 0, 1, 0, 0, "瑞雲12型(六三四空)", 0, 0, 0, 4,
                    0, 7, 0, 0, 0, 6, 3))
            .put(82, new ItemDto(82, 8, 8, 0, 0, 0, 0, 0, 0, 0, 0, "九七式艦攻(九三一空)", 6, 0, 0, 2,
                    0, 2, 0, 0, 0, 7, 0))
            .put(83, new ItemDto(83, 8, 8, 0, 0, 0, 0, 0, 0, 0, 0, "天山(九三一空)", 9, 0, 0, 3,
                    0, 2, 0, 0, 0, 8, 0))
            .put(84, new ItemDto(84, 21, 15, 0, 0, 0, 0, 0, 1, 0, 0, "2cm 四連装FlaK 38", 0, 0, 0, 3,
                    0, 0, 0, 0, 0, 0, 7))
            .put(85, new ItemDto(85, 21, 15, 0, 0, 0, 1, 0, 1, 0, 0, "3.7cm FlaK M42", 0, 0, 0, 3,
                    0, 0, 0, 0, 0, 0, 8))
            .put(86, new ItemDto(86, 31, 26, 0, 0, 0, 0, 0, 0, 0, 0, "艦艇修理施設", 0, 0, 0, 3,
                    0, 0, 0, 0, 0, 0, 0))
            .put(87, new ItemDto(87, 17, 19, 0, 0, 0, 0, 13, 0, 0, 0, "新型高温高圧缶", 0, 0, 0, 3,
                    0, 0, 0, 0, 0, 0, 0))
            .put(88, new ItemDto(88, 12, 11, 0, 0, 0, 0, 0, 8, 0, 0, "22号対水上電探改四", 0, 0, 0, 3,
                    0, 5, 0, 0, 0, 2, 0))
            .put(89, new ItemDto(89, 13, 11, 0, 0, 0, 0, 1, 3, 0, 0, "21号対空電探改", 0, 0, 0, 3,
                    0, 6, 0, 0, 0, 0, 5))
            .put(90, new ItemDto(90, 2, 2, 0, 0, 0, 9, 0, 1, 2, 0, "20.3cm(2号)連装砲", 0, 0, 0, 1,
                    0, 0, 0, 0, 0, 0, 3))
            .put(91, new ItemDto(91, 1, 16, 0, 0, 0, 2, 1, 1, 1, 0, "12.7cm連装高角砲(後期型)", 0, 0, 0, 1,
                    0, 0, 0, 0, 0, 1, 5))
            .put(92, new ItemDto(92, 21, 15, 0, 0, 0, 0, 1, 0, 0, 0, "毘式40mm連装機銃", 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 6))
            .put(93, new ItemDto(93, 8, 8, 0, 0, 0, 0, 0, 3, 0, 0, "九七式艦攻(友永隊)", 11, 0, 0, 4,
                    0, 4, 0, 0, 0, 5, 1))
            .put(94, new ItemDto(94, 8, 8, 0, 0, 0, 0, 0, 3, 0, 0, "天山一二型(友永隊)", 14, 0, 0, 5,
                    0, 5, 0, 0, 0, 6, 1))
            .put(95, new ItemDto(95, 32, 5, 0, 0, 0, 0, 0, 3, 1, 0, "潜水艦53cm艦首魚雷(8門)", 16, 0, 0, 2,
                    0, 0, 0, 0, 0, 0, 0))
            .put(96, new ItemDto(96, 6, 6, 0, 0, 0, 0, 2, 2, 0, 0, "零式艦戦21型(熟練)", 0, 0, 0, 3,
                    0, 1, 0, 0, 0, 0, 8))
            .put(97, new ItemDto(97, 7, 7, 0, 0, 7, 0, 0, 2, 0, 0, "九九式艦爆(熟練)", 0, 0, 0, 3,
                    0, 2, 0, 0, 0, 4, 1))
            .put(98, new ItemDto(98, 8, 8, 0, 0, 0, 0, 0, 2, 0, 0, "九七式艦攻(熟練)", 8, 0, 0, 3,
                    0, 2, 0, 0, 0, 5, 0))
            .put(99, new ItemDto(99, 7, 7, 0, 0, 10, 0, 0, 4, 0, 0, "九九式艦爆(江草隊)", 0, 0, 0, 4,
                    0, 3, 0, 0, 0, 5, 0))
            .put(100, new ItemDto(100, 7, 7, 0, 0, 13, 0, 0, 4, 0, 0, "彗星(江草隊)", 0, 0, 0, 5,
                    0, 4, 0, 0, 0, 5, 1))
            .put(101, new ItemDto(101, 33, 27, 0, 0, 0, 0, 0, 0, 0, 0, "照明弾", 0, 0, 0, 1,
                    0, 0, 0, 0, 0, 0, 0))
            .put(102, new ItemDto(102, 10, 10, 0, 0, 0, 0, 0, 1, 0, 0, "九八式水上偵察機(夜偵)", 0, 0, 0, 3,
                    0, 3, 0, 0, 0, 1, 0))
            .put(103, new ItemDto(103, 3, 3, 0, 0, 0, 18, 0, 2, 3, 0, "試製35.6cm三連装砲", 0, 0, 0, 4,
                    0, 0, 0, 0, 0, 0, 5))
            .put(104, new ItemDto(104, 3, 3, 0, 0, 0, 15, 1, 1, 3, 0, "35.6cm連装砲(ダズル迷彩)", 0, 0, 0, 3,
                    0, 0, 0, 0, 0, 0, 5))
            .put(105, new ItemDto(105, 3, 3, 0, 0, 0, 22, 0, 2, 3, 0, "試製41cm三連装砲", 0, 0, 0, 4,
                    0, 0, 0, 0, 0, 0, 5))
            .put(106, new ItemDto(106, 12, 11, 0, 0, 0, 0, 1, 2, 0, 0, "13号対空電探改", 0, 0, 0, 3,
                    0, 4, 0, 0, 0, 0, 4))
            .put(107, new ItemDto(107, 34, 28, 0, 0, 0, 0, 1, 1, 0, 0, "艦隊司令部施設", 0, 0, 0, 5,
                    0, 1, 0, 0, 0, 0, 1))
            .put(108, new ItemDto(108, 35, 29, 0, 0, 0, 10, 0, 1, 3, 0, "熟練艦載機整備員", 0, 0, 0, 3,
                    0, 1, 0, 0, 0, 0, 1))
            .put(109, new ItemDto(109, 6, 6, 0, 0, 0, 0, 1, 1, 0, 0, "零戦52型丙(六〇一空)", 0, 0, 0, 3,
                    0, 0, 0, 0, 0, 0, 9))
            .put(110, new ItemDto(110, 6, 6, 0, 0, 0, 0, 2, 1, 0, 0, "烈風(六〇一空)", 0, 0, 0, 4,
                    0, 0, 0, 0, 0, 0, 11))
            .put(111, new ItemDto(111, 7, 7, 0, 0, 11, 0, 0, 1, 0, 0, "彗星(六〇一空)", 0, 0, 0, 3,
                    0, 1, 0, 0, 0, 4, 0))
            .put(112, new ItemDto(112, 8, 8, 0, 0, 0, 0, 0, 1, 0, 0, "天山(六〇一空)", 10, 0, 0, 3,
                    0, 2, 0, 0, 0, 4, 0))
            .put(113, new ItemDto(113, 8, 8, 0, 0, 0, 0, 0, 1, 0, 0, "流星(六〇一空)", 13, 0, 0, 4,
                    0, 3, 0, 0, 0, 5, 0))
            .put(114, new ItemDto(114, 3, 3, 0, 0, 0, 17, 0, 3, 3, 0, "38cm連装砲改", 0, 0, 0, 2,
                    0, 0, 0, 0, 0, 0, 2))
            .put(115, new ItemDto(115, 10, 10, 0, 0, 1, 0, 0, 2, 0, 0, "Ar196改", 0, 0, 0, 2,
                    0, 5, 0, 0, 0, 5, 1))
            .put(116, new ItemDto(116, 19, 13, 0, 0, 0, 9, 0, 2, 0, 0, "一式徹甲弾", 0, 0, 0, 3,
                    0, 0, 0, 0, 0, 0, 0))
            .put(117, new ItemDto(117, 3, 3, 0, 0, 0, 23, 0, 1, 4, 0, "試製46cm連装砲", 0, 0, 0, 4,
                    0, 0, 0, 0, 0, 0, 4))
            .put(118, new ItemDto(118, 10, 10, 0, 0, 1, 0, 0, 1, 0, 0, "紫雲", 0, 0, 0, 4,
                    0, 8, 0, 0, 0, 2, 0))
            .put(119, new ItemDto(119, 2, 2, 0, 0, 0, 3, 0, 2, 2, 0, "14cm連装砲", 0, 0, 0, 1,
                    0, 0, 0, 0, 0, 0, 0))
            .put(120, new ItemDto(120, 36, 30, 0, 0, 0, 0, 1, 0, 0, 0, "91式高射装置", 0, 0, 0, 1,
                    0, 0, 0, 0, 0, 0, 2))
            .put(121, new ItemDto(121, 36, 30, 0, 0, 0, 0, 1, 0, 0, 0, "94式高射装置", 0, 0, 0, 2,
                    0, 0, 0, 0, 0, 0, 3))
            .put(122, new ItemDto(122, 1, 16, 0, 0, 0, 3, 1, 1, 1, 0, "10cm連装高角砲+高射装置", 0, 0, 0, 3,
                    0, 0, 0, 0, 0, 0, 10))
            .put(123, new ItemDto(123, 2, 2, 0, 0, 0, 10, 0, 3, 2, 0, "SKC34 20.3cm連装砲", 0, 0, 0, 3,
                    0, 0, 0, 0, 0, 0, 2))
            .put(124, new ItemDto(124, 13, 11, 0, 0, 0, 3, 0, 10, 0, 0, "FuMO25 レーダー", 0, 0, 0, 4,
                    0, 9, 0, 0, 0, 0, 7))
            .put(125, new ItemDto(125, 5, 5, 0, 0, 0, 0, 1, 0, 1, 0, "61cm三連装(酸素)魚雷", 8, 0, 0, 2,
                    0, 0, 0, 1, 0, 0, 0))
            .put(126, new ItemDto(126, 37, 31, 0, 0, 0, 1, 0, 0, 1, 0, "WG42 (Wurfgerät 42)", 0, 0, 0, 4,
                    0, 0, 0, -1, 0, 0, 0))
            .put(127, new ItemDto(127, 32, 5, 0, 0, 0, 0, 2, 7, 1, 0, "試製FaT仕様九五式酸素魚雷改", 14, 0, 0, 5,
                    0, 0, 0, 0, 0, 0, 0))
            .put(128, new ItemDto(128, 3, 3, 0, 0, 0, 30, -1, 1, 4, 0, "試製51cm連装砲", 0, 0, 0, 5,
                    0, 0, 0, 0, 0, 0, 5))
            .put(129, new ItemDto(129, 39, 32, 0, 0, 0, 0, 3, 2, 0, 0, "熟練見張員", 0, 0, 0, 3,
                    0, 2, 0, 0, 0, 0, 1))
            .put(130, new ItemDto(130, 4, 16, 0, 0, 0, 1, 1, 1, 1, 0, "12.7cm高角砲+高射装置", 0, 0, 0, 3,
                    0, 0, 0, 0, 0, 0, 8))
            .put(131, new ItemDto(131, 21, 15, 0, 0, 0, 0, 1, 0, 0, 0, "25mm三連装機銃 集中配備", 0, 0, 0, 3,
                    0, 0, 0, 0, 0, 0, 9))
            .put(132, new ItemDto(132, 40, 18, 0, 0, 0, 0, 1, 1, 0, 0, "零式水中聴音機", 0, 0, 0, 3,
                    0, 1, 0, 0, 0, 11, 0))
            .put(133, new ItemDto(133, 3, 3, 0, 0, 0, 20, -1, -3, 4, 0, "381mm/50 三連装砲", 0, 0, 0, 4,
                    0, 0, 0, 0, 0, 0, 2))
            .put(134, new ItemDto(134, 4, 4, 0, 0, 0, 8, 0, 1, 2, 0, "OTO 152mm三連装速射砲", 0, 0, 0, 3,
                    0, 0, 0, 1, 0, 0, 2))
            .put(135, new ItemDto(135, 4, 16, 0, 0, 0, 1, 0, 1, 1, 0, "90mm単装高角砲", 0, 0, 0, 3,
                    0, 0, 0, 0, 0, 0, 8))
            .put(136, new ItemDto(136, 28, 23, 0, 0, 0, 0, -1, 0, 0, 0, "プリエーゼ式水中防御隔壁", 0, 0, 0, 3,
                    0, 0, 0, 7, 0, 0, 0))
            .put(137, new ItemDto(137, 3, 3, 0, 0, 0, 21, -1, -1, 4, 0, "381mm/50 三連装砲改", 0, 0, 0, 5,
                    0, 0, 0, 0, 0, 0, 4))
            .put(138, new ItemDto(138, 41, 33, 0, 0, 0, 0, 0, 1, 0, 0, "二式大艇", 0, 0, 0, 4,
                    0, 12, 0, 0, 0, 1, 0))
            .put(139, new ItemDto(139, 2, 2, 0, 0, 0, 5, 0, 4, 2, 0, "15.2cm連装砲改", 0, 0, 0, 3,
                    0, 0, 0, 0, 0, 0, 3))
            .put(140, new ItemDto(140, 42, 24, 0, 0, 0, 0, 0, 0, 0, 0, "96式150cm探照灯", 0, 0, 0, 3,
                    0, 3, 0, 0, 0, 0, 1))
            .put(141, new ItemDto(141, 13, 11, 0, 0, 0, 0, 0, 9, 0, 0, "32号対水上電探改", 0, 0, 0, 4,
                    0, 11, 0, 0, 0, 0, 0))
            .put(501, new ItemDto(501, 1, 1, 0, 0, 0, 1, 0, 0, 1, 0, "5inch単装砲", 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0))
            .put(502, new ItemDto(502, 1, 1, 0, 0, 0, 2, 0, 0, 1, 0, "5inch連装砲", 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0))
            .put(503, new ItemDto(503, 1, 16, 0, 0, 0, 1, 0, 0, 1, 0, "3inch単装高角砲", 0, 0, 0, 2,
                    0, 0, 0, 0, 0, 0, 1))
            .put(504, new ItemDto(504, 2, 2, 0, 0, 0, 2, 0, 0, 2, 0, "5inch単装高射砲", 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 2))
            .put(505, new ItemDto(505, 2, 2, 0, 0, 0, 8, 0, 0, 2, 0, "8inch三連装砲", 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 2))
            .put(506, new ItemDto(506, 2, 2, 0, 0, 0, 3, 0, 0, 2, 0, "6inch連装速射砲", 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 3))
            .put(507, new ItemDto(507, 3, 3, 0, 0, 0, 10, 0, 0, 3, 0, "14inch連装砲", 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 4))
            .put(508, new ItemDto(508, 3, 3, 0, 0, 0, 15, 0, 0, 3, 0, "16inch連装砲", 0, 0, 16, 1,
                    0, 0, 0, 0, 0, 0, 4))
            .put(509, new ItemDto(509, 3, 3, 0, 0, 0, 20, 0, 0, 3, 0, "16inch三連装砲", 0, 0, 10, 2,
                    0, 0, 0, 0, 0, 0, 5))
            .put(510, new ItemDto(510, 4, 16, 0, 0, 0, 1, 0, 0, 1, 0, "5inch単装高射砲", 0, 0, 10, 0,
                    0, 0, 0, 0, 0, 0, 2))
            .put(511, new ItemDto(511, 4, 4, 0, 0, 0, 1, 0, 0, 2, 0, "6inch単装砲", 0, 0, 13, 0,
                    0, 0, 0, 0, 0, 0, 0))
            .put(512, new ItemDto(512, 4, 4, 0, 0, 0, 7, 0, 0, 2, 0, "12.5inch連装副砲", 0, 0, 3, 1,
                    0, 0, 0, 0, 0, 0, 3))
            .put(513, new ItemDto(513, 5, 5, 0, 0, 0, 0, 0, 0, 1, 0, "21inch魚雷前期型", 2, 0, 8, 0,
                    0, 0, 0, 0, 0, 0, 0))
            .put(514, new ItemDto(514, 5, 5, 0, 0, 0, 0, 0, 0, 1, 0, "21inch魚雷後期型", 5, 0, 6, 0,
                    0, 0, 0, 0, 0, 0, 0))
            .put(515, new ItemDto(515, 5, 5, 0, 0, 0, 0, 0, 0, 1, 0, "高速深海魚雷", 10, 0, 4, 1,
                    0, 0, 0, 0, 0, 0, 0))
            .put(516, new ItemDto(516, 8, 8, 0, 0, 0, 0, 0, 0, 0, 0, "深海棲艦攻", 4, 0, 0, 0,
                    0, 5, 0, 0, 0, 2, 0))
            .put(517, new ItemDto(517, 8, 8, 0, 0, 0, 0, 0, 0, 0, 0, "深海棲艦攻 Mark.II", 6, 0, 2, 1,
                    0, 5, 0, 0, 0, 4, 0))
            .put(518, new ItemDto(518, 8, 8, 0, 0, 0, 0, 0, 0, 0, 0, "深海棲艦攻 Mark.III", 11, 0, 0, 2,
                    0, 5, 0, 0, 0, 7, 4))
            .put(519, new ItemDto(519, 6, 6, 0, 0, 0, 0, 0, 0, 0, 0, "深海棲艦戦", 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 2))
            .put(520, new ItemDto(520, 6, 6, 0, 0, 0, 0, 0, 0, 0, 0, "深海棲艦戦 Mark.II", 0, 0, 24, 0,
                    0, 0, 0, 0, 0, 0, 5))
            .put(521, new ItemDto(521, 6, 6, 0, 0, 0, 0, 0, 0, 0, 0, "深海棲艦戦 Mark.III", 0, 0, 0, 1,
                    0, 0, 0, 0, 0, 0, 9))
            .put(522, new ItemDto(522, 6, 6, 0, 0, 0, 0, 0, 0, 0, 0, "飛び魚艦戦", 0, 0, 7, 3,
                    0, 0, 0, 0, 0, 0, 13))
            .put(523, new ItemDto(523, 7, 7, 0, 0, 3, 0, 0, 0, 0, 0, "深海棲艦爆", 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 1, 0))
            .put(524, new ItemDto(524, 7, 7, 0, 0, 6, 0, 0, 0, 0, 0, "深海棲艦爆 Mark.II", 0, 0, 0, 1,
                    0, 0, 0, 0, 0, 2, 0))
            .put(525, new ItemDto(525, 10, 10, 0, 0, 1, 0, 0, 0, 0, 0, "深海棲艦偵察機", 0, 0, 0, 0,
                    0, 5, 0, 0, 0, 1, 1))
            .put(526, new ItemDto(526, 10, 10, 0, 0, 4, 0, 0, 0, 0, 0, "飛び魚偵察機", 0, 0, 0, 1,
                    0, 10, 0, 0, 0, 2, 2))
            .put(527, new ItemDto(527, 12, 11, 0, 0, 0, 0, 0, 5, 0, 0, "対空レーダ― Mark.I", 0, 0, 0, 1,
                    0, 5, 0, 0, 0, 0, 5))
            .put(528, new ItemDto(528, 12, 11, 0, 0, 0, 0, 0, 10, 0, 0, "水上レーダ― Mark.I", 0, 0, 0, 1,
                    0, 5, 0, 0, 0, 0, 0))
            .put(529, new ItemDto(529, 12, 11, 0, 0, 0, 0, 0, 15, 0, 0, "水上レーダ― Mark.II", 0, 0, 0, 2,
                    0, 10, 0, 0, 0, 0, 0))
            .put(530, new ItemDto(530, 13, 11, 0, 0, 0, 0, 0, 5, 0, 0, "対空レーダ― Mark.II", 0, 0, 21, 2,
                    0, 10, 0, 0, 0, 0, 10))
            .put(531, new ItemDto(531, 13, 11, 0, 0, 0, 0, 3, 24, 0, 0, "深海水上レーダー", 0, 0, 13, 3,
                    0, 16, 0, 0, 0, 5, 5))
            .put(532, new ItemDto(532, 13, 11, 0, 0, 0, 0, 2, 16, 0, 0, "深海対空レーダ―", 0, 0, 13, 4,
                    0, 12, 0, 0, 0, 5, 18))
            .put(533, new ItemDto(533, 17, 19, 0, 0, 0, 0, 10, 0, 0, 0, "改良型深海タービン", 0, 0, 17, 0,
                    0, 0, 0, 0, 0, 0, 0))
            .put(534, new ItemDto(534, 17, 19, 0, 0, 0, 0, 15, 0, 0, 0, "強化型深海缶", 0, 0, 4, 1,
                    0, 0, 0, 0, 0, 0, 0))
            .put(535, new ItemDto(535, 18, 12, 0, 0, 0, 0, 0, 0, 0, 0, "対空散弾", 0, 0, 10, 0,
                    0, 0, 0, 0, 0, 0, 10))
            .put(536, new ItemDto(536, 19, 13, 0, 0, 0, 15, 0, 5, 0, 0, "劣化徹甲弾", 0, 0, 8, 1,
                    0, 0, 0, 0, 0, 0, 0))
            .put(537, new ItemDto(537, 21, 15, 0, 0, 0, 0, 0, 0, 0, 0, "12.7mm機銃", 0, 0, 5, 0,
                    0, 0, 0, 0, 0, 0, 2))
            .put(538, new ItemDto(538, 21, 15, 0, 0, 0, 0, 0, 0, 0, 0, "20mm機銃", 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 4))
            .put(539, new ItemDto(539, 21, 15, 0, 0, 0, 0, 0, 0, 0, 0, "40mm二連装機関砲", 0, 0, 3, 1,
                    0, 0, 0, 0, 0, 0, 8))
            .put(540, new ItemDto(540, 21, 15, 0, 0, 0, 0, 0, 0, 0, 0, "40mm四連装機関砲", 0, 0, 0, 2,
                    0, 0, 0, 0, 0, 0, 12))
            .put(541, new ItemDto(541, 22, 5, 0, 0, 0, 0, 0, 5, 0, 0, "深海烏賊魚雷", 18, 0, 0, 4,
                    0, 0, 0, 0, 0, 0, 0))
            .put(542, new ItemDto(542, 15, 17, 0, 0, 0, 0, 0, 0, 0, 0, "深海爆雷投射機", 0, 0, 0, 2,
                    0, 0, 0, 0, 0, 7, 0))
            .put(543, new ItemDto(543, 14, 18, 0, 0, 0, 0, 0, 0, 0, 0, "深海ソナー", 0, 0, 0, 2,
                    0, 0, 0, 0, 0, 9, 0))
            .put(544, new ItemDto(544, 15, 17, 0, 0, 0, 0, 0, 0, 0, 0, "深海爆雷投射機 Mk.II", 0, 0, 0, 3,
                    0, 0, 0, 0, 0, 13, 0))
            .put(545, new ItemDto(545, 14, 18, 0, 0, 0, 0, 0, 0, 0, 0, "深海ソナー Mk.II", 0, 0, 0, 3,
                    0, 0, 0, 0, 0, 16, 0))
            .put(546, new ItemDto(546, 7, 7, 0, 0, 10, 0, 0, 0, 0, 0, "飛び魚艦爆", 0, 0, 0, 3,
                    0, 0, 0, 0, 0, 7, 8))
            .put(547, new ItemDto(547, 6, 6, 0, 0, 0, 0, 0, 1, 0, 0, "深海猫艦戦", 0, 0, 0, 3,
                    0, 0, 0, 0, 0, 0, 10))
            .put(548, new ItemDto(548, 7, 7, 0, 0, 11, 0, 0, 3, 0, 0, "深海地獄艦爆", 0, 0, 0, 3,
                    0, 3, 0, 0, 0, 4, 0))
            .put(549, new ItemDto(549, 8, 8, 0, 0, 0, 0, 0, 2, 0, 0, "深海復讐艦攻", 13, 0, 0, 3,
                    0, 5, 0, 0, 0, 5, 4))
            .put(550, new ItemDto(550, 1, 16, 0, 0, 0, 2, 0, 3, 2, 0, "5inch連装両用莢砲", 0, 0, 0, 3,
                    0, 0, 0, 0, 0, 0, 9))
            .put(551, new ItemDto(551, 3, 3, 0, 0, 0, 27, 0, 3, 3, 0, "20inch連装砲", 0, 0, 0, 4,
                    0, 0, 0, 0, 0, 0, 4))
            .put(552, new ItemDto(552, 3, 3, 0, 0, 0, 13, 0, 4, 3, 0, "15inch要塞砲", 0, 0, 0, 2,
                    0, 0, 0, 3, 0, 0, 0))
            .build();

    /**
     * アイテムを設定します
     */
    public static void set(int id, ItemDto item) {
        ITEM.put(id, item);
    }

    /**
     * アイテムを取得します
     *
     * @param type ID
     * @return アイテム
     */
    public static ItemDto get(int type) {
        return ITEM.get(type);
    }

    /**
     * IDの一覧を取得します
     *
     * @return IDの一覧
     */
    public static Set<Integer> keySet() {
        return ITEM.keySet();
    }
}
