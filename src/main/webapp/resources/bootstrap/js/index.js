'use strict';

window.onload = function () {
    var KEYMAP = {
            '只狼': 8,
            'CSGO': 9,
            'DOTA2': 13,
            '死亡搁浅': 16,
            '荒野大镖客2': 17,
            '尼尔机械纪元': 18,
            '半条命': 20,
            '光环': 32,
            '传送门': 33,
            '空洞骑士': 34,
            '蔚蓝': 35,
            '逆转裁判': 36,
            '生化危机1': 37,
            '鬼泣1': 38,
            'Artifact': 39,
            '刀塔霸业': 40,
            '铲子骑士': 45,
            '三色绘恋': 46,
            '太吾绘卷': 48,
            '茶杯头': 49,
            '生化危机2': 50,
            '彩虹六号': 51,
            '黑魂1': 52,
            '冰与火之舞': 53,
            '地狱老司机': 54,
            '绝地求生': 55,
            '鬼泣2': 56,
            '地狱前兵': 57,
            '人类一败涂地': 65,
            '坦克大战': 66,
            '命运': 67,
            '黎明杀机': 68,
            '求生之路': 69,
            'NEKO PARA': 70,
            '艾希': 71,
            '命运2': 72,
            '美好世界': 73,
            'inside': 74,
            'limbo': 75,
            'room': 76,
            '分手厨房': 77,
            '心跳文学部': 78,
            'Team Fortress 2': 79,
            '军团要塞': 80,
            '文明': 81,
            '杀手': 82,
            '收获日2': 83,
            '刺客信条起源': 84,
            '怪物猎人': 85,
            'GTA2': 86,
            '古墓丽影9': 87,
            '收获日2': 88,
            '掘地求生': 89,
            '文明5': 90,
            '勇敢的心': 112,
            '黑手党3': 113,
            '特技摩托': 114,
            'GTA3': 115,
            'VRCHAT': 116,
            '狼与香辛料': 117,
            '黑暗血统': 118,
            '天空商人': 119,
            '文明4': 120,
            '秋叶原之旅': 121,
            '隐形守护者': 122,
            '荣耀战魂': 123,
            '公路救赎': 124,
            'GTA4': 125,
            '尘埃拉力赛2': 126,
            '消逝的光芒': 186,
            '血源': 187,
            '黑相集': 188,
            '雨中冒险': 189,
            '巫师3': 190,
            '饥荒': 191,
            '上古卷轴5': 219,
            'GTA5': 220,
            '传送门骑士': 221,
            '黑魂3': 222
        },
        key_els = {};

    var rand = function rand() {
        var max = arguments.length <= 0 || arguments[0] === undefined ? 1 : arguments[0];
        var min = arguments.length <= 1 || arguments[1] === undefined ? 0 : arguments[1];

        var _int = arguments.length <= 2 || arguments[2] === undefined ? 0 : arguments[2];

        var gen = min + (max - min) * Math.random();
        return _int ? Math.round(gen) : gen;
    };

    (function init() {
        var a3d = document.querySelector('.a3d'),
            f = document.createDocumentFragment(),
            lims = [33, 41, 47, 58, 91, 127, 146],
            len = lims.length,
            unit = 360 / (len + 1);

        for (var p in KEYMAP) {
            var rot = document.createElement('div'),
                key = document.createElement('div');

            key.dataset.name = p.replace('NUM_', '');
            key.dataset.code = KEYMAP[p];
            key.classList.add('key');

            for (var i = 0; i < len; i++) {
                if (KEYMAP[p] < lims[i]) {
                    var hue = rand((i + .8) * unit, (i + .2) * unit, 1);
                    key.style.color = 'hsl(' + hue + ',100%,65%)';
                    break;
                }
            }

            rot.classList.add('rot');

            key_els[p] = key;
            rot.appendChild(key);
            f.appendChild(rot);
        }

        a3d.appendChild(f);
    })();

    addEventListener('keydown', function (e) {
        e.preventDefault();

        for (var p in KEYMAP) {
            if (e.keyCode === KEYMAP[p]) {
                if (!key_els[p].classList.contains('vis')) key_els[p].classList.add('vis');
                return;
            }
        }
    }, false);

    addEventListener('keyup', function (e) {
        e.preventDefault();
    }, false);

    addEventListener('animationend', function (e) {
        var t = e.target;
        if (e.animationName === 'hl') t.classList.remove('vis');
    }, false);
};