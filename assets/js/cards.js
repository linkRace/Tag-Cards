var tags = [], cards = [], selected = [], cardOn = 0, slideShow = false, currentCard = 0, cardFlipped = false, startEnglish = false, studySide = "japanese";

$(document).ready(function(){
  $("#study-mode").hide();
  $(document).on( "click","#insertCard", function() {
    insert();
  });
  $(document).on( "click",".studyButton", function() {
    $("#study-mode").toggle();
    $("#nonStudy").toggle();
    if ($("#study-mode").is(":visible")) {
      slideShow = true;
      currentCard = 0;
      cardFlipped = false;
      if ($("#anyKanji").is(':checked')) {
        studySide = "japanese";
      } else {
        studySide = "kanji";
      }
      if ($("#whichFirst").is(':checked')) {
        startEnglish = true;
      } else {
        startEnglish = false;
      }
      if ($("#shuffle").is(':checked')) {
        selected = shuffle(selected);
      }
      advanceCard(true);
    } else {
      slideShow = false;
    }
  });
  $(document).on( "click",".space", function(e) {
    $(this).toggleClass("pure-button-primary");
    getSelectedCards();
  });
  $(document).bind('keyup', function(e){
    if(e.which === 120) {
      insert();
    }
    if (slideShow) {
      if(e.which === 32) {
        flipCard();
      }
      if(e.which === 13 || e.which === 39) {
        advanceCard(true);
      }
      if(e.which === 37) {
        advanceCard(false);
      }
    }
  });
  $(document).on("keyup", "#newJapanese", function() {
    var car = $("#newJapanese").val(), x = car[car.length - 1];
    if (x === x.toUpperCase()) {
      car = car.replace(/ô/gi, "oー");
      car = car.replace(/û/gi, "uー");
      car = car.replace(/â/gi, "aー");
      car = car.replace(/î/gi, "iー");
      car = car.replace(/ê/gi, "eー");

      car = car.replace(/n/gi, "ン");
      car = car.replace(/v/gi, "ヴ");
      car = car.replace(/q/gi, "ッ");
      car = car.replace(/shi/gi, "シ");
      car = car.replace(/chi/gi, "チ");
      car = car.replace(/tsu/gi, "ツ");
      car = car.replace(/kya/gi, "キャ");
      car = car.replace(/kyu/gi, "キュ");
      car = car.replace(/kyo/gi, "キョ");
      car = car.replace(/ンya/gi, "ニャ");
      car = car.replace(/ンyu/gi, "ニュ");
      car = car.replace(/ンyo/gi, "ニョ");
      car = car.replace(/sha/gi, "シャ");
      car = car.replace(/shu/gi, "シュ");
      car = car.replace(/sho/gi, "ショ");
      car = car.replace(/cha/gi, "チャ");
      car = car.replace(/chu/gi, "チュ");
      car = car.replace(/cho/gi, "チョ");
      car = car.replace(/hya/gi, "ヒャ");
      car = car.replace(/hyu/gi, "ヒュ");
      car = car.replace(/hyo/gi, "ヒョ");
      car = car.replace(/mya/gi, "ミャ");
      car = car.replace(/myu/gi, "ミュ");
      car = car.replace(/myo/gi, "ミョ");
      car = car.replace(/rya/gi, "リャ");
      car = car.replace(/ryu/gi, "リュ");
      car = car.replace(/ryo/gi, "リョ");
      car = car.replace(/gya/gi, "ギャ");
      car = car.replace(/gyu/gi, "ギュ");
      car = car.replace(/gyo/gi, "ギョ");
      car = car.replace(/bya/gi, "ビャ");
      car = car.replace(/byu/gi, "ビュ");
      car = car.replace(/byo/gi, "ビョ");
      car = car.replace(/pya/gi, "ピャ");
      car = car.replace(/pyu/gi, "ピュ");
      car = car.replace(/pyo/gi, "ピョ");
      car = car.replace(/ja/gi, "ジャ");
      car = car.replace(/ju/gi, "ジュ");
      car = car.replace(/jo/gi, "ジョ");
      car = car.replace(/ba/gi, "バ");
      car = car.replace(/da/gi, "ダ");
      car = car.replace(/ga/gi, "ガ");
      car = car.replace(/ha/gi, "ハ");
      car = car.replace(/ka/gi, "カ");
      car = car.replace(/ma/gi, "マ");
      car = car.replace(/ンa/gi, "ナ");
      car = car.replace(/pa/gi, "パ");
      car = car.replace(/ra/gi, "ラ");
      car = car.replace(/sa/gi, "サ");
      car = car.replace(/ta/gi, "タ");
      car = car.replace(/wa/gi, "ワ");
      car = car.replace(/ya/gi, "ヤ");
      car = car.replace(/za/gi, "ザ");
      car = car.replace(/a/gi, "ア");
      car = car.replace(/be/gi, "ベ");
      car = car.replace(/de/gi, "デ");
      car = car.replace(/ge/gi, "ゲ");
      car = car.replace(/he/gi, "ヘ");
      car = car.replace(/ke/gi, "ケ");
      car = car.replace(/me/gi, "メ");
      car = car.replace(/ンe/gi, "ネ");
      car = car.replace(/pe/gi, "ペ");
      car = car.replace(/re/gi, "レ");
      car = car.replace(/se/gi, "セ");
      car = car.replace(/te/gi, "テ");
      car = car.replace(/we/gi, "ヱ");
      car = car.replace(/ze/gi, "ゼ");
      car = car.replace(/[eé]/gi, "エ");
      car = car.replace(/bi/gi, "ビ");
      car = car.replace(/gi/gi, "ギ");
      car = car.replace(/hi/gi, "ヒ");
      car = car.replace(/ki/gi, "キ");
      car = car.replace(/mi/gi, "ミ");
      car = car.replace(/ンi/gi, "ニ");
      car = car.replace(/pi/gi, "ピ");
      car = car.replace(/ri/gi, "リ");
      car = car.replace(/wi/gi, "ヰ");
      car = car.replace(/ji/gi, "ジ");
      car = car.replace(/bo/gi, "ボ");
      car = car.replace(/do/gi, "ド");
      car = car.replace(/go/gi, "ゴ");
      car = car.replace(/ho/gi, "ホ");
      car = car.replace(/ko/gi, "コ");
      car = car.replace(/mo/gi, "モ");
      car = car.replace(/ンo/gi, "ノ");
      car = car.replace(/po/gi, "ポ");
      car = car.replace(/ro/gi, "ロ");
      car = car.replace(/so/gi, "ソ");
      car = car.replace(/to/gi, "ト");
      car = car.replace(/wo/gi, "ヲ");
      car = car.replace(/yo/gi, "ヨ");
      car = car.replace(/zo/gi, "ゾ");
      car = car.replace(/o/gi, "オ");
      car = car.replace(/bu/gi, "ブ");
      car = car.replace(/gu/gi, "グ");
      car = car.replace(/fu/gi, "フ");
      car = car.replace(/ku/gi, "ク");
      car = car.replace(/mu/gi, "ム");
      car = car.replace(/ンu/gi, "ヌ");
      car = car.replace(/pu/gi, "プ");
      car = car.replace(/ru/gi, "ル");
      car = car.replace(/su/gi, "ス");
      car = car.replace(/yu/gi, "ユ");
      car = car.replace(/zu/gi, "ズ");

      // cas des doublons
      car = car.replace(/di/gi, "ヂ");
      car = car.replace(/ジ=/gi, "ヂ");
      car = car.replace(/du/gi, "ヅ");
      car = car.replace(/ズ=/gi, "ヅ");

      car = car.replace(/i/gi, "イ");
      car = car.replace(/u/gi, "ウ");

      car = car.replace(/ア=/gi, "ァ");
      car = car.replace(/イ=/gi, "ィ");
      car = car.replace(/ウ=/gi, "ゥ");
      car = car.replace(/エ=/gi, "ェ");
      car = car.replace(/オ=/gi, "ォ");
      car = car.replace(/ツ=/gi, "ッ");
      car = car.replace(/ヤ=/gi, "ャ");
      car = car.replace(/ユ=/gi, "ュ");
      car = car.replace(/ヨ=/gi, "ョ");

      car = car.replace(/\./gi, "。");
      car = car.replace(/\,/gi, "、");
      car = car.replace(/\!/gi, "！");
      car = car.replace(/\?/gi, "？");
      car = car.replace(/\:/gi, "：");
      car = car.replace(/\(/gi, "（");
      car = car.replace(/\)/gi, "）");
      car = car.replace(/\[/gi, "［");　
      car = car.replace(/\]/gi, "］");
      car = car.replace(/\«/gi, "「");
      car = car.replace(/\»/gi, "」");
    } else {
      car = car.replace(/ô/g, "oう");
      car = car.replace(/û/g, "uう");
      car = car.replace(/â/g, "aあ");
      car = car.replace(/î/g, "iい");
      car = car.replace(/ê/g, "eい");

      car = car.replace(/n/g, "ん");
      car = car.replace(/q/g, "っ");
      car = car.replace(/tsu/g, "つ");
      car = car.replace(/kya/g, "きゃ");
      car = car.replace(/kyu/g, "きゅ");
      car = car.replace(/kyo/g, "きょ");
      car = car.replace(/んya/g, "にゃ");
      car = car.replace(/んyu/g, "にゅ");
      car = car.replace(/んyo/g, "にょ");
      car = car.replace(/sha/g, "しゃ");
      car = car.replace(/shi/g, "し");
      car = car.replace(/shu/g, "しゅ");
      car = car.replace(/sho/g, "しょ");
      car = car.replace(/chi/g, "ち");
      car = car.replace(/cha/g, "ちゃ");
      car = car.replace(/chu/g, "ちゅ");
      car = car.replace(/cho/g, "ちょ");
      car = car.replace(/hya/g, "ひゃ");
      car = car.replace(/hyu/g, "ひゅ");
      car = car.replace(/hyo/g, "ひょ");
      car = car.replace(/mya/g, "みゃ");
      car = car.replace(/myu/g, "みゅ");
      car = car.replace(/myo/g, "みょ");
      car = car.replace(/rya/g, "りゃ");
      car = car.replace(/ryu/g, "りゅ");
      car = car.replace(/ryo/g, "りょ");
      car = car.replace(/gya/g, "ぎゃ");
      car = car.replace(/gyu/g, "ぎゅ");
      car = car.replace(/gyo/g, "ぎょ");
      car = car.replace(/bya/g, "びゃ");
      car = car.replace(/byu/g, "びゅ");
      car = car.replace(/byo/g, "びょ");
      car = car.replace(/pya/g, "ぴゃ");
      car = car.replace(/pyu/g, "ぴゅ");
      car = car.replace(/pyo/g, "ぴょ");
      car = car.replace(/ja/g, "じゃ");
      car = car.replace(/ju/g, "じゅ");
      car = car.replace(/jo/g, "じょ");
      car = car.replace(/ba/g, "ば");
      car = car.replace(/da/g, "だ");
      car = car.replace(/ga/g, "が");
      car = car.replace(/ha/g, "は");
      car = car.replace(/ka/g, "か");
      car = car.replace(/ma/g, "ま");
      car = car.replace(/んa/g, "な");
      car = car.replace(/pa/g, "ぱ");
      car = car.replace(/ra/g, "ら");
      car = car.replace(/sa/g, "さ");
      car = car.replace(/ta/g, "た");
      car = car.replace(/wa/g, "わ");
      car = car.replace(/ya/g, "や");
      car = car.replace(/za/g, "ざ");
      car = car.replace(/a/g, "あ");
      car = car.replace(/be/g, "べ");
      car = car.replace(/de/g, "で");
      car = car.replace(/ge/g, "げ");
      car = car.replace(/he/g, "へ");
      car = car.replace(/ke/g, "け");
      car = car.replace(/me/g, "め");
      car = car.replace(/んe/g, "ね");
      car = car.replace(/pe/g, "ぺ");
      car = car.replace(/re/g, "れ");
      car = car.replace(/we/g, "ゑ");
      car = car.replace(/se/g, "せ");
      car = car.replace(/te/g, "て");
      car = car.replace(/ze/g, "ぜ");
      car = car.replace(/[eé]/g, "え");
      car = car.replace(/bi/g, "び");
      car = car.replace(/gi/g, "ぎ");
      car = car.replace(/hi/g, "ひ");
      car = car.replace(/ki/g, "き");
      car = car.replace(/mi/g, "み");
      car = car.replace(/んi/g, "に");
      car = car.replace(/pi/g, "ぴ");
      car = car.replace(/ri/g, "り");
      car = car.replace(/wi/g, "ゐ");
      car = car.replace(/ji/g, "じ");
      car = car.replace(/bo/g, "ぼ");
      car = car.replace(/do/g, "ど");
      car = car.replace(/go/g, "ご");
      car = car.replace(/ho/g, "ほ");
      car = car.replace(/ko/g, "こ");
      car = car.replace(/mo/g, "も");
      car = car.replace(/んo/g, "の");
      car = car.replace(/po/g, "ぽ");
      car = car.replace(/ro/g, "ろ");
      car = car.replace(/so/g, "そ");
      car = car.replace(/to/g, "と");
      car = car.replace(/wo/g, "を");
      car = car.replace(/yo/g, "よ");
      car = car.replace(/zo/g, "ぞ");
      car = car.replace(/o/g, "お");
      car = car.replace(/bu/g, "ぶ");
      car = car.replace(/gu/g, "ぐ");
      car = car.replace(/fu/g, "ふ");
      car = car.replace(/ku/g, "く");
      car = car.replace(/mu/g, "む");
      car = car.replace(/んu/g, "ぬ");
      car = car.replace(/pu/g, "ぷ");
      car = car.replace(/ru/g, "る");
      car = car.replace(/su/g, "す");
      car = car.replace(/yu/g, "ゆ");
      car = car.replace(/zu/g, "ず");

      // cas des doublons
      car = car.replace(/di/g, "ぢ");
      car = car.replace(/じ=/g, "ぢ");
      car = car.replace(/du/g, "づ");
      car = car.replace(/ず=/g, "づ");

      car = car.replace(/i/g, "い");
      car = car.replace(/u/g, "う");
      car = car.replace(/v/g, "ゔ");

      car = car.replace(/つ=/g, "っ");
      car = car.replace(/あ=/g, "ぁ");
      car = car.replace(/い=/g, "ぃ");
      car = car.replace(/う=/g, "ぅ");
      car = car.replace(/え=/g, "ぇ");
      car = car.replace(/お=/g, "ぉ");
      car = car.replace(/や=/g, "ゃ");
      car = car.replace(/ゆ=/g, "ゅ");
      car = car.replace(/よ=/g, "ょ");

      car = car.replace(/\./g, "。");
      car = car.replace(/\,/g, "、");
      car = car.replace(/\!/g, "！");
      car = car.replace(/\?/g, "？");
      car = car.replace(/\:/g, "：");
      car = car.replace(/\(/g, "（");
      car = car.replace(/\)/g, "）");
      car = car.replace(/\[/g, "［");　
      car = car.replace(/\]/g, "］");
      car = car.replace(/\«/g, "「");
      car = car.replace(/\»/g, "」");
    }
    $("#newJapanese").val(car);
  })
  refreshList();
});

function shuffle(array) {
  var currentIndex = array.length, temporaryValue, randomIndex;

  // While there remain elements to shuffle...
  while (0 !== currentIndex) {

    // Pick a remaining element...
    randomIndex = Math.floor(Math.random() * currentIndex);
    currentIndex -= 1;

    // And swap it with the current element.
    temporaryValue = array[currentIndex];
    array[currentIndex] = array[randomIndex];
    array[randomIndex] = temporaryValue;
  }

  return array;
}

function flipCard() {
  cardFlipped = !cardFlipped;
  if (cardFlipped) {
    $("#card").html(selected[currentCard - 1][studySide]);
  } else {
    $("#card").html(selected[currentCard - 1].english);
  }
}

function advanceCard(forward) {
  if (forward) {
    ++currentCard;
  } else {
    --currentCard;
  }
  if (currentCard > selected.length) {
    currentCard = selected.length + 1; // keeps it from going way over end bound
    $("#card").html("All Done!");
    $("#cardProgress").html("End of Cards");
  } else {
    cardFlipped = !startEnglish;
    $("#cardProgress").html("Card " + currentCard + " / " + selected.length);
    if (cardFlipped) {
      $("#card").html(selected[currentCard - 1][studySide]);
    } else {
      $("#card").html(selected[currentCard - 1].english);
    }
  }
}

function getSelectedCards() {
  var buttons = $(".pure-button-primary"), selectedTags = [];
  selected = [];
  for (var i = 1, length = buttons.length; i < length; ++i) {
    selectedTags.push(buttons[i].innerHTML);
  }
  var stLength = selectedTags.length, st = 0, matched;
  for (var c = 0, length = cards.length; c < length; ++c) {
    matched = 0;
    for (st = 0; st < stLength; ++st) {
      if (matched === st) {
        for (var t = 0, length2 = cards[c].tags.length; t < length2; ++t) {
          if (selectedTags[st] === cards[c].tags[t]) {
            ++matched;
            break;
          }
        }
      }
    }
    if (matched === stLength) {
      selected.push(cards[c]);
    }
  }
  for (var cardList = "", i = 0, length = selected.length; i < length; ++i) {
    cardList += "<div class='row'>\
      <div class='4u'>" + selected[i].english + "</div>\
      <div class='4u'>" + selected[i].japanese + "</div>\
      <div class='4u'>" + selected[i].tags + "</div>\
    </div>";
  }
  $("#allCards").html(cardList);
}

function refreshList() {
  $.get("card/findCards", function(resp) {
    tags = [], cards = [], selected = [];
    for (var cardList = "", i = 0, length = resp.length; i < length; ++i) {
      cards.push(resp[i]);
      for (var j = 0, length2 = resp[i].tags.length; j < length2; ++j) {
        if ($.inArray(resp[i].tags[j], tags) === -1) {
          tags.push(resp[i].tags[j]);
        }
      }
    }
    for (var tagList = "", k = 0, length3 = tags.length; k < length3; ++k) {
      tagList += "<button class='pure-button space'>" + tags[k] + "</button>";
    }
    $("#allTags").html(tagList);
    getSelectedCards();
  });
}

function clearInput() {
  $("#newEnglish").val("");
  $("#newKanji").val("");
  $("#newJapanese").val("");
}

function insert() {
  var english = $("#newEnglish").val(),
  japanese = $("#newJapanese").val(),
  kanji = $("#newKanji").val(),
  tags = $("#newTags").val();
  $.post("card/insert", {
    english: english,
    japanese: japanese,
    kanji: kanji,
    tags: tags
  }, function(err,resp) {
    if (resp === "success") {
      console.log("success");
      refreshList();
      clearInput();
    } else {
      console.log("failure");
    }
  });
}

String.prototype.replaceAll = function(search, replace)
{
  return this.split(search).join(replace);
};
