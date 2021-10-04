const VIOLET_VERSION = '1.0.0';

const RAND_MAX = 1000;
const BOSS_GAME_RAND_MAX = 100;
const ANI_PAGE_RAND_MAX = 100;
const ANI_RECOMMEND_RAND_MAX = 1000;

const TWO_HOUR_SEC = 7200;
const ONE_HOUR_SEC = 3600;
const ONE_TWO_THTREE_SEC = 1234;
const TEN_MIN_SEC = 600;

const LOTTO_NUM_MAX = 6;
const LOTTO_RAND_MAX = 45;

/* 기본 응답어 */
var hello_msg =      ['안녕', '안뇽', '안냥', '하이', 'ㅎㅇ'];
var hello_reply =   [
         '「안녕하십니까.」',
         '「안녕하세요?」',
         '「안녕하세요.」'
         ];

var morning_msg =    ['좋은아침', '좋은 아침', '굿모닝', 'ㄱㅁㄴ'];
var morning_reply = [
         '「좋은아침 입니다.」',
         '「오늘도 기분 좋은 하루 되시길 바랍니다.」',
         '「오늘 하루도 힘냅시다.」'
         ]; 

var bye_msg =    ['잘자', 'ㅂㅂ', '굿나잇', '바이', 'ㅂ2', 'ㅂㅇ'];
var bye_reply =   [
         '「안녕히 주무십시오.」',
         '「고생하셨습니다.」',
         '「수고하셨습니다.」'
         ]; 

var violet_msg =   ['바이올렛', '에버가든', '바에'];
var violet_reply =   [
         '「고객님이 원하신다면 어디든 달려가겠습니다. 자동 수기 인형 서비스 바이올렛 에버가든입니다.」',
         '「우리들은 그저 주어진 이 목숨을 어떻게 살아가야 할지 생각할 수 밖에 없습니다.」',
         '「서로가, 자신들의 주장을 고집하는 것이 전쟁이라는 것입니다.」',
         '「고객님, 무슨일이십니까.」',
         '「무슨일이시죠?」',
         '「부르셨나요?」',
         '「이용해주셔서 감사합니다.」',
         '「이제 명령은 필요 없어요. 」'
         ];
         
var violet_love_msg =    ['사랑', '아이시테루', 'love', 'LOVE'];
var violet_love_reply =   [
         '「알고 싶습니다. ‘사랑해’를... 알고 싶습니다.」',
         '「소중한 사람과 헤어진다는 것은.. 두 번 다시 만나지 못한다는 것은.. 이렇게나 쓸쓸하고 이렇게나 괴로운 것이었군요.」',
         '「‘사랑해’는 무척 용기가 필요한 말이네요. 받아들이지 않으면 거기에 있고 싶지 않을 정도로..」'
         ];
         
var violet_gil_msg =    ['길베르트'];
var violet_gil_reply =   [
         '「소령님..」',
         '「지금 어디에 계십니까..」',
         '「언젠가..」',
         '「기다리고 있습니다.」',
         '「소령님은 살아계십니다.」'
         ];
         
var violet_what_msg =    ['뭐해', '뭐하', '뭐햐', '뭐행', '머해', '머하', '머행', '모해', '모하', '모행'];
var violet_what_reply =   [
         '「지금은 시를 쓰고 있습니다.」',
         '「잠시 졸았습니다..」',
         '「편지를 쓰고 있었습니다.」',
         '「만화란 것을 보고 있었습니다. 참으로 흥미진진 하군요.」',
         '「과자를 먹고 있습니다.」',
         '「오늘따라 일이 많군요.」',
         '「잠시 요리를 하고 있습니다.」',
         '「열심히 공부중 입니다.」',
         '「오늘의 하늘을 바라보고 있었습니다.」'
         ];

var kkk_msg =       ['ㅋㅋㅋㅋ'];
var kkk_reply =    [
         '「그렇게 웃기시나요?」',
         '「저도 같이 웃고 싶습니다.」',
         '「웃으면 복이 온다고 해요.」',
         '「정말 재미있군요?」',
         '「알고 싶습니다. ‘웃음’을... 알고 싶습니다.」'
         ];

var chool_msg =       ['출근'];
var chool_reply =    [
         '「잘 다녀오십시오.」',
         '「저는 이미 출근했습니다.」',
         '「조심히 다녀오세요.」',
         '「힘내십시오.」'
         ];
         
var toi_msg =       ['퇴근'];
var toi_reply =    [
         '「고생 많으셨습니다.」',
         '「저도 퇴근하고 싶습니다.」',
         '「듣기만해도 행복한 말이군요.」',
         '「푹 쉬세요.」',
         '「수고하셨습니다.」'
         ];   
   
var wow_msg =       ['어우', '오오', '오우', 'ㅗㅜㅑ'];
var wow_reply =    [
         '「대박이네요.」',
         '「대박입니다.」',
         '「큰일입니다.」',
         '「와..」',
         '「오호.」'
         ];

/* 바이올렛 명령어 */
var help_msg =       ['도움말', '--help', '-h'];
var nalssi_msg =    ['날씨'];
var silsigan_msg =    ['실시간 검색어', '인기 검색어', '검색어', '실검'];
var meet_msg =       ['환영'];
var coin_msg =      ['비트코인', '이더리움', '리플'];
var cal_msg =      ['칼로리'];
var lotto_msg =      ['로또', 'lotto', 'LOTTO'];
var ramen_msg =      ['라면추천', '라면 추천'];
var ramen_content =   ['신라면', '진라면', '안성탕면', '삼양라면', '너구리', '무파마', '남자라면',
         '꼬꼬면', '진짬뽕', '맛짬뽕', '공화춘', '짜파게티', '짜왕', '팔도비빔면',
         '불닭볶음면', '틈새라면'];
var ramen_sub_content =   ['계란', '파송송', '치즈', '참치', '삼겹살', '푸아그라', '슈바인학센',
         '글라쉬나', '파전', '취두부', '홍어', '수르스트뢰밍', '멘보샤', '도마뱀',
         '꽃', '향수', '태풍', '고추기름', '물 한사바리', '제로콜라', '장아찌'];

var chik_msg =      ['치킨추천', '치킨 추천'];
var chik_reply =   ['「BBQ 어떠십니까.」', '「맘스터치 어떠십니까.」', '「BHC 어떠십니까.」', 
         '「페리카나 어떠십니까.」', '「굽네치킨 어떠십니까.」', '「네네치킨 어떠십니까.」', 
         '「노랑통닭 어떠십니까.」', '「교촌치킨 어떠십니까.」', '「멕시카나 어떠십니까.」', 
         '「부어치킨 어떠십니까.」', '「순수치킨 어떠십니까.」', 
         '「호식이 두마리 치킨 어떠십니까.」', '「처갓집 어떠십니까.」', 
         '「바른치킨 어떠십니까.」', '「멕시칸 어떠십니까.」', '「또래오래 어떠십니까.」', 
         '「깐부치킨 어떠십니까.」', '「치킨플러스 어떠십니까.」', '「KFC 어떠십니까.」'];
         
var recommend_ani_msg =   ['애니추천', '만화추천', '애니 추천', '만화 추천', '추천애니', '추천만화', 
         '추천 애니', '추천 만화'];
var today_ani_msg =    ['오늘의 애니', '오늘의애니', '오늘 애니', '오늘애니',
         '오늘의 만화', '오늘의만화', '오늘 만화', '오늘만화'];
         
var meal_msg =       ['아침추천', '점심추천', '저녁추천', '아침 추천', '점심 추천', '저녁 추천',
         '뭐먹', '메뉴', '밥', '식사'];
var meal_reply =    ['「짜장면 어떠십니까.」', '「짬뽕 어떠십니까.」', '「탕수육 어떠십니까.」', '「초밥 어떠십니까.」',
         '「돈부리 어떠십니까.」', '「소바 어떠십니까.」', '「라면 어떠십니까.」', 
         '「돼지국밥 어떠십니까.」', '「삼겹살정식 어떠십니까.」', '「갈비탕 어떠십니까.」', '「비빔국수 어떠십니까.」', 
         '「스테이크 어떠십니까.」', '「햄버거 어떠십니까.」', '「피자 어떠십니까.」', '「파스타 어떠십니까.」', 
         '「만두 어떠십니까.」', '「카레 어떠십니까.」', '「훈제오리 어떠십니까.」', '「냉면 어떠십니까.」', 
         '「된장찌개 어떠십니까.」', '「닭도리탕 어떠십니까.」', '「김치찌개 어떠십니까.」', 
         '「불고기 어떠십니까.」', '「떡볶이 어떠십니까.」', '「순대국밥 어떠십니까.」', '「보쌈 어떠십니까.」'];

/* 호감도 명령어 */
var hogam_up_msg =    ['이뻐', '귀여', '좋아', '착해', '똑똑', '최고', 
         '예뻐', '귀엽', '커엽', '귀욤', '귀요', '멋져', '멋있'];
var hogam_down_msg =    ['바보', '멍청', '못생', '싫', '나뻐', '나쁜',
         '돼지', '뚱땡', '미워', '너무해', '흥', '그만', '냄',
         '저리', '최악', '나빠', '죽어', '별로'];
var hogam_sender =    [];
var hogam_sender_value= [];

/* 공부하기 명령어 */
var study_msg =    ['공부하기', '학습하기', '배우기'];
var study_check_msg =    ['공부내용', '학습내용', '배운내용'];
var study_del_msg =    ['잊어버려'];
var study_req =    [];
var study_rsp =    [];

/* 미니게임 명령어 */
var game_msg =       ['미니게임'];
var boss_game_msg =    ['퍽', '펀치', '이얍'];
var game_start_flag = 0;
var game_start_cool_time = 0;
const BOSS_VIOLET_HP = 2000;
var game_hp = BOSS_VIOLET_HP;

/* 2021 설연휴 자음퀴즈 이벤트 */
/*
var ani_quiz_msg = ['애니퀴즈'];
var ani_quiz_problem = ['ㅁㄴㅂㄷㅂㅋㅋ', 'ㅅㅅㄱㅇㅂㄱㄹㅇ', 'ㅇㅍㅅ', 'ㄷㅅㄴㅌ', 'ㅅㅋㄹㅂ',
                  'ㄴㄹㅎㄴㅇㅈㄴ', 'ㅊㅇㄷㅍㄱㄹㄹㄱ', 'ㄷㅇㅌㅇㄹㅇㅂ', 'ㅇㅅ', 'ㄱㅅㅅ', 
                  'ㄷㄹㅇㄷㅍㄹㅋㅅ', 'ㄹㅈㅇㅍㄹㅅ', 'ㅇㅅㅇㄴㅂㄹㄷ', 'ㅂㄹㅋㄹㅂ', 'ㅈㅅㅎㅈ',
                  'ㅂㅇㅇㅅㅎㅁ', 'ㅎㅇㅇㅇㅈㅇㄴㅅ', 'ㅇㄱㅅㅇㄴㄴㄱㅇㅇㄱㅁㅇㅆㄷ', 'ㅇㅂㅇㅇㅊㅇㅇㅅㅇㄲㅇㅈㅅㅎㅈ',
                  'ㅇㄹㄷㅇㄹㅅㅌㄷㅈㅇㅁㅇㅇㅅㄴㅇㅊㅂㅁㅇㅇㅅㅅㄴㄷㅎㅇㅇㄱ'];
var ani_quiz_answer  = ['마녀배달부키키', '신세기에반게리온', '원피스', '데스노트', '스쿨럼블',
                  '노래하는왕자님', '천원돌파그렌라간', '데이트어라이브', '일상', '기생수', 
                  '달링인더프랑키스', '리즈와파랑새', '약속의네버랜드', '블랙클로버', '주술회전',
                  '반요야샤히메', '하울의움직이는성', '울고싶은나는고양이가면을쓴다', '이별의아침에약속의꽃을장식하자',
                  '예를들어라스트던전앞마을의소년이초반마을에서사는듯한이야기'];
*/
/* 2021 어린이날 기념 추억의 애니퀴즈 이벤트 */
var ani_quiz_msg = ['애니퀴즈'];
var ani_quiz_problem = ['ㄷㅂㅊㅅ', 'ㅅㅍㄷㅇㅂㄱ', 'ㄹㅋ', 'ㄷㅇㅇㅈㅅㅇㄹㅁ', 'ㄲㄹㄱㅅㅂㄷ',
                  'ㅍㅇㅍㅍㄱ', 'ㅁㅅㅇㅅㅅ', 'ㅇㄷㅍㅊ', 'ㅁㅂㄱㅅㄹㅇㅇㅅ', 'ㅂㄴㅂㄴ',
                  'ㅅㅊㅇㅌㅈㄸㅂ', 'ㅂㄴㄹㅇㅈㅋㅁ', 'ㅎㄱㄹㅂㄱㄷㄹ', 'ㄱㅉㄱㅈ', 'ㄷㅈㅁㅇㄷㅂㅊ',
                  'ㄴㅅㅈㅊㅎㅁㅅ', 'ㅊㅅㅅㄴㄴㅌ', 'ㅈㅅㅇㅇㅅㄷㄱ', 'ㅂㄱㅅㄴㄴㅊㄱ', 'ㅂㄱㅂㄱㅎㅌㄹ',
                  'ㄷㄷㄷ', 'ㅃㄱㅁㅌㅊㅊ', 'ㄱㅅㄷㅈ', 'ㅂㅊㄷㅅㅁㄷㅅ', 'ㅅㅍㄱㅈ',
                  'ㅇㄹㅋㅈㄹㅋ', 'ㅁㄹㅋㄴㅇㅎㅅ', 'ㅇㅂㄲㅂㅇㅇㄴㅇㅈㅇ', 'ㅈㅅㅇㅁㅂㅋㄹㅋㄹ', 'ㅎㅇㅁㅇㅂㄱ'];
var ani_quiz_answer  = ['달빛천사', '스피드왕번개', '레카', '달의요정세일러문', '꾸러기수비대',
                  '파워퍼프걸', '미소의세상', '웨딩피치', '마법기사레이어스', '보노보노',
                  '사차원탐정똘비', '별나라요정코미', '황금로봇골드런', '괴짜가족', '디지몬어드벤쳐',
                  '녹색전차해모수', '천사소녀네티', '전설의용사다간', '보거스는내친구', '방가방가햄토리',
                  '다다다', '빨강망토차차', '구슬동자', '배추도사무도사', '슈퍼갤즈',
                  '요리킹조리킹', '마루코는아홉살', '은비까비의옛날옛적에', '전설의마법쿠루쿠루', '하얀마음백구'];
                  
/* 금지어 */
var yok_msg =       ['ㅅㅂ','시발','시빨','씨발','씨빠','씨빨','슈발','싀발','슈빨','쓔발',
         '쓔빨','씌발','싀빨','씌발','ㅆㅃ','ㅅㅃ','ㅆㅃ','ㅅㅍ','시팔','씨팔', 
         'ㅄ','ㅂㅅ','병신','븅신','또라이','미친놈','미친년','개새끼','뒤져','좆',
         'ㅅ1ㅂ','ㅅ@ㅂ','시이발','씨댕','개빡','시파','싀파','싀팔','싀바','꺼져',
         '꺼저','도라이'];


function basic_response(msg, replier, req_msg, rsp_msg) {
   var rand = Math.floor(Math.random() * RAND_MAX);

   for (var i=0; i < req_msg.length; i++) {
      if (msg.indexOf(req_msg[i]) != -1) {
         java.lang.Thread.sleep(500);
         replier.reply(rsp_msg[rand % rsp_msg.length]);
         return 0;
      }
   }

   return -1;
}

var ani_quiz_index = 0;
var ani_quiz_start = 0;
var ani_quiz_answer_flag = 1;
function ani_quiz_response(msg, replier, req_msg, ani_quiz_msg, ani_answer_msg) {
   var now_time;
   
   for (var i=0; i < req_msg.length; i++) {
      if (msg.indexOf(req_msg[i]) != -1) {
         if (ani_quiz_start == 0) {
            ani_quiz_start = 1;
            replier.reply("「잠시후 어린이날 기념 추억의 애니퀴즈 이벤트가 있을 예정이옵니다. 많은 참여 부탁드립니다ㅎㅎ」");
            java.lang.Thread.sleep(ONE_TWO_THTREE_SEC * 1000);
            for (ani_quiz_index=0; ani_quiz_index < ani_quiz_msg.length; ) {
               ani_quiz_index++;
               ani_quiz_answer_flag = 0;
               /*
               replier.reply("「2021 설연휴 기념 애니자음퀴즈 이벤트 " + ani_quiz_index + "번째 입니다.」\n\n" 
                           + ani_quiz_msg[ani_quiz_index - 1] + "\n(띄어쓰기 없이 입력해주세요!)"); 
               */
               replier.reply("「2021 어린이날 기념 추억의 애니퀴즈 이벤트☆ (" + ani_quiz_index + "/30)」\n\n" 
                           + ani_quiz_msg[ani_quiz_index - 1] + "\n(띄어쓰기 없이 입력해주세요!)"); 
               
               java.lang.Thread.sleep(ONE_TWO_THTREE_SEC * 1000);
               
               if (ani_quiz_answer_flag == 0) {
                  ani_quiz_answer_flag = 1;
                  replier.reply("「정답은 " + ani_answer_msg[ani_quiz_index - 1] + " 였습니다.」");
               }
            }
            
         }
         else {
            replier.reply("「이미 퀴즈 이벤트 진행중입니다!」");
         }
         
         return 0;
      }
   }
   
   return -1;
}

function ani_quiz_answer_response(msg, replier, req_msg, sender) {
   if (ani_quiz_start) {
      if (ani_quiz_answer_flag == 0) {
         if (msg.indexOf(req_msg[ani_quiz_index - 1]) != -1) {
            replier.reply("「" + sender +"님 정답입니다!」");
            ani_quiz_answer_flag = 1;
            return 0;
         }
      }
   }
   
   return -1;
}

function lotto_response(msg, replier, req_msg) {
   var rand = 0;
   var num = [0,0,0,0,0,0];
   
   for (var i=0; i < req_msg.length; i++) {
      if (msg.indexOf(req_msg[i]) != -1) {
         var lotto_index = 0;
         var flag = 0;
         
         while (lotto_index < LOTTO_NUM_MAX) {
            rand = Math.floor((Math.random() * LOTTO_RAND_MAX) + 1);
            flag = 0;
            
            for (var j=0; j<LOTTO_NUM_MAX; j++) {
               if (num[j] == rand) {
                  flag = 1;
               }
            }
            
            if (flag == 0) {
               num[lotto_index++] = rand;
            }
         }
         
         num.sort(function(a, b) {return a - b;});
         replier.reply('「' + num[0] +', '+ num[1] +', '+ num[2] +', '+ num[3] +', '+ num[4] +', '+ num[5] + ' 정도 추천드립니다.」');
         return 0;
      }
   }
   
   return -1
}

function ramen_response(msg, replier, req_msg, rsp_main_msg, rsp_sub_msg) {
   var main_rand = Math.floor(Math.random() * RAND_MAX);
   var sub_rand = Math.floor(Math.random() * RAND_MAX);

   for (var i=0; i < req_msg.length; i++) {
      if (msg.indexOf(req_msg[i]) != -1) {
         java.lang.Thread.sleep(500);
         replier.reply("「" + rsp_sub_msg[sub_rand % rsp_sub_msg.length] + " 넣은 " + rsp_main_msg[main_rand % rsp_main_msg.length] + " 추천 드립니다.」");
         return 0;
      }
   }

   return -1;
}


function sometimes_basic_response(msg, replier, req_msg, rsp_msg) {
   var sometimes_rand = Math.floor(Math.random() * RAND_MAX);
   var rand = Math.floor(Math.random() * RAND_MAX);

   if (sometimes_rand > (RAND_MAX / 3)) {
      return -1;
   }
   
   for (var i=0; i < req_msg.length; i++) {
      if (msg.indexOf(req_msg[i]) != -1) {
         java.lang.Thread.sleep(500);
         replier.reply(rsp_msg[rand % rsp_msg.length]);
         return 0;
      }
   }
   
   return -1;
}


function getCoinPrice(pos) {
   var price;
   var coin_name;
   var result;
   
   if (pos == "비트코인") {
      coin_name = "BTC";
   }
   else if (pos == "이더리움") {
      coin_name = "ETH";
   }
   else if (pos == "리플") {
      coin_name = "XRP";
   }
   else {
      result = "「고객님, 정보 제공을 지원하지 않는 코인입니다.」";
      return result;
   }

   price = Utils.getHtmlFromWeb("https://api.upbit.com/v1/ticker?markets=KRW-" + coin_name)

   price = price.split(",")[9]
   price = price.split(":")[1]
   price = price.split(".")[0]
    
   result = "「현재 "+pos+" 시세는 "+price+"원 인것 같습니다.」";
    return result;
}


function coin_response(msg, replier, req_msg) {
   var split_str;
   
   for (var i=0; i < req_msg.length; i++) {
      if (msg.indexOf(req_msg[i]) != -1) {
         replier.reply(getCoinPrice(req_msg[i]));
         return 0;
      }
   }
   
   return -1
}


function getCal(pos) {
    var data = Utils.getWebText("http://www.dietshin.com/"
    +"calorie/calorie_search.asp?keyword="+pos);
    data = data.replace(/(<([^>]+)>)/g, "");
    data = data.replace(/ /gi, "");
    data = data.split("음식명\n칼로리")[1];
    data = data.split("kcal")[0];
    data = data.trim();
   data = data.replace("\n","");

    var result = "「" + data + "kcal 인것 같습니다.」";

    if (result.length > pos.length + 30) {
        result = "「칼로리 정보를 찾지 못하였습니다.」"
    }
    return result;
}


function cal_response(msg, replier, req_msg) {
   var index;
   var split_str;
   
   for (var i=0; i < req_msg.length; i++) {
      if (msg.indexOf(req_msg[i]) != -1) {
         index = msg.indexOf(req_msg[i]) + 4;
         
         if (index < msg.length) {
            split_str = msg.substring(index, msg.length);
            replier.reply(getCal(split_str));
         }
         else {
            replier.reply('「고객님, 죄송하지만 잘 알아 듣지 못하였습니다. "칼로리 + 음식" 형태의 문장으로 말씀해주십시오.」');
         }
         return 0;
      }
   }
   
   return -1
}


function hogam_up_response(msg, replier, req_msg, sender) {
   var exist_flag = 0;
   for (var i=0; i < req_msg.length; i++) {
      if (msg.indexOf(req_msg[i]) != -1) {
         for (var j=0; j < hogam_sender.length; j++) {
            if (hogam_sender[j].indexOf(sender) != -1) {
               hogam_sender_value[j]++;
               exist_flag = 1;
               
               replier.reply("「감사합니다.」 (" + hogam_sender[j] + "님의 호감도 : " + hogam_sender_value[j] + ")");
            }
         }
         
         if (exist_flag == 0) {
            hogam_sender.push(sender);
            hogam_sender_value.push(1);
            
            replier.reply("「감사합니다.」 (" + sender + "님의 호감도 : 1)");
         }
         
         return 0;
      }
   }
   
   return -1;
}


function hogam_down_response(msg, replier, req_msg, sender) {
   var exist_flag = 0;
   for (var i=0; i < req_msg.length; i++) {
      if (msg.indexOf(req_msg[i]) != -1) {
         for (var j=0; j < hogam_sender.length; j++) {
            if (hogam_sender[j].indexOf(sender) != -1) {
               hogam_sender_value[j]--;
               exist_flag = 1;
               
               replier.reply("... (" + hogam_sender[j] + "님의 호감도 : " + hogam_sender_value[j] + ")");
            }
         }
         
         if (exist_flag == 0) {
            hogam_sender.push(sender);
            hogam_sender_value.push(-1);
            
            replier.reply("... (" + sender + "님의 호감도 : -1)");
         }
         
         return 0;
      }
   }
   
   return -1;
}


function study_req_rsp(msg, isGroupChat, replier, req_msg) {
   var first_msg_start_index = -1;
   var first_msg_end_index = -1;
   var second_msg_start_index = -1;
   var second_msg_end_index = -1;

   if (isGroupChat == 0) {
      return -1;
   }
   
   for (var i=0; i < req_msg.length; i++) {
      if (msg.indexOf(req_msg[i]) != -1) {
         first_msg_start_index = msg.indexOf('[') + 1;
         first_msg_end_index = msg.indexOf(']');
         second_msg_start_index = msg.lastIndexOf('[') + 1;
         second_msg_end_index = msg.lastIndexOf(']');
         
         if ((first_msg_start_index != -1 && first_msg_end_index != -1 &&
         second_msg_start_index != -1 && second_msg_end_index != -1) && 
         (first_msg_start_index < first_msg_end_index &&
         first_msg_end_index < second_msg_start_index &&
         second_msg_start_index < second_msg_end_index)) {
            study_req.push(msg.substring(first_msg_start_index, first_msg_end_index));
            study_rsp.push(msg.substring(second_msg_start_index, second_msg_end_index));
            
            replier.reply('「명심하겠습니다.」');
         }
         else {
            replier.reply('「다시 한 번 말씀해주십시오.」 ("공부하기 [배울 문장] [응답 문장]")');
         }
         return 0;
      }
   }
   
   return -1;
}


function study_response(msg, replier, req_msg, rsp_msg) {
   for (var i=0; i < req_msg.length; i++) {
      if (msg.indexOf(req_msg[i]) != -1) {
         java.lang.Thread.sleep(500);
         replier.reply(rsp_msg[i]);
         return 0;
      }
   }
   
   return -1;
}


function study_check_response(msg, replier, req_msg) {
   var result = '「기록된 내용은 아래와 같습니다.」\n\n';
   
   for (var i=0; i < req_msg.length; i++) {
      if (msg.indexOf(req_msg[i]) != -1) {
         if (study_req.length == 0) {
            java.lang.Thread.sleep(500);
            replier.reply('-');
            return 0;
         }
         
         for (var j=0; j < study_req.length; j++) {
            result += '[' + study_req[j] + '] [' + study_rsp[j] + ']\n' ;
         }
         
         java.lang.Thread.sleep(500);
         replier.reply(result);
         return 0;
      }
   }
   
   return -1;
}


function study_del_response(msg, replier, req_msg) {
   for (var i=0; i < req_msg.length; i++) {
      if (msg.indexOf(req_msg[i]) != -1) {
         study_req.splice(0, study_req.length);
         study_rsp.splice(0, study_rsp.length);
         
         java.lang.Thread.sleep(500);
         replier.reply('「기록 삭제 완료하였습니다.」');
         return 0;
      }
   }
   
   return -1;
}


function help_response(msg, replier, req_msg) {
   for (var i=0; i < req_msg.length; i++) {
      if (msg.indexOf(req_msg[i]) != -1) {
         java.lang.Thread.sleep(500);
         replier.reply('/*\n * Violet Evergarden Bot\n *      Version ' + VIOLET_VERSION + '\n */' +
            '\n\n 「"바이올렛", "에버가든", "바에" + "명령어"」 형태로 동작합니다.\n\n' +
            '명령어 목록은 아래와 같습니다.\n   - 도움말, -h, --help\n   - 환영하기\n   - 뭐해\n   - 날씨\n   - 아침, 점심, 저녁추천\n   - 라면추천\n   - 치킨추천\n   - 애니추천\n   - 오늘의 애니\n   - 공부하기\n   - 실시간 검색어\n   - 비트코인\n   - 칼로리\n   - 로또번호');
         return 0;
      }
   }
   
   return -1;
}


function nalssi_response(msg, replier, req_msg) {
   for (var i=0; i < req_msg.length; i++) {
      if (msg.indexOf(req_msg[i]) != -1) {
         replier.reply("「링크를 첨부하였으니 확인 바랍니다.」\nhttps://www.google.com/search?q=날씨");   
         return 0;
      }
   }
   
   return -1;
}


function silsigan_response(msg, replier, req_msg) {
   for (var i=0; i < req_msg.length; i++) {
      if (msg.indexOf(req_msg[i]) != -1) {
         try { 
            var url = "https://www.naver.com/srchrank?frm=main&ag=all&gr=1&ma=-2&si=0&en=0&sp=0"; 
            var json = Utils.getWebText(url); 
            json = json.replace(/(<([^>]+)>)/ig, "");
            var keywords = []; 
            var datas = JSON.parse(json); 
            
            for (var j in datas["data"]) { 
               var keywordData = datas["data"][j];
               var str = keywordData["rank"] + ". " + keywordData["keyword"]; 
               keywords.push(str);
            }
            replier.reply("「현재 인기 키워드를 기록하였습니다.」\n\n" + keywords.join("\n")); 
         } catch (e) { 
            replier.reply("「고객님, 죄송하지만 잘 알아 듣지 못하였습니다.」"); 
         } 
         
         return 0;
      }
   }
   
   return -1;
}


function meet_response(msg, replier, req_msg) {
   for (var i=0; i < req_msg.length; i++) {
      if (msg.indexOf(req_msg[i]) != -1) {
         replier.reply("「고객님이 원하신다면 어디든 달려가겠습니다. 자동 수기 인형 서비스 바이올렛 에버가든입니다.\n\n닉네임은 이름/나이/최애캐 설정해주시고, 간단한 자기소개(입문작/최애작/최애캐/가장 최근에 본 애니) 부탁드립니다.」");
         return 0;
      }
   }
   
   return -1;
}


function yok_response(msg, replier, req_msg) {
   for (var i=0; i < req_msg.length; i++) {
      if (msg.indexOf(req_msg[i]) != -1) {
         replier.reply("「 '" + req_msg[i] + "' 이런 말 쓰시면 안되옵니다.」");
         return 0;
      }
   }
   
   return -1;
}


function today_ani_response(msg, replier, req_msg) {
   var today = new Date();
   var json_start_index = -1;
   var json_end_index = -1;
            
   for (var i=0; i < req_msg.length; i++) {
      if (msg.indexOf(req_msg[i]) != -1) {
         
         try {
            var url = "https://anissia.net/api/anime/schedule/" + today.getDay(); 
            var json = Utils.getWebText(url); 
            json = json.replace(/(<([^>]+)>)/ig, "");
            var keywords = []; 
            
            json_start_index = json.indexOf('[');
            json_end_index = json.lastIndexOf(']') + 1;

            var tmp = json.substring(json_start_index, json_end_index);
            var datas = JSON.parse(tmp);
            
            for (var j in datas) { 
               var keywordData = datas[j];
               var str = " - " + keywordData["subject"] + " (" + keywordData["genres"] + ")" + "\n   : " + keywordData["website"]; 
               keywords.push(str);
            }
            
            replier.reply("「오늘 방영하는 애니를 기록하였습니다.」\n\n" + keywords.join("\n")); 
         } catch (e) { 
            replier.reply("「고객님, 죄송하지만 잘 알아 듣지 못하였습니다.」"); 
         } 
         
         return 0;
      }
   }

   return -1
}


function recommend_ani_response(msg, replier, req_msg) {
   var page_rand = Math.floor(Math.random() * ANI_PAGE_RAND_MAX);
   var subject_rand = Math.floor(Math.random() * ANI_RECOMMEND_RAND_MAX);
   var json_start_index = -1;
   var json_end_index = -1;
   
   for (var i=0; i < req_msg.length; i++) {
      if (msg.indexOf(req_msg[i]) != -1) {
         
         try {
            var url = "https://anissia.net/api/anime/list/" + page_rand;
            var json = Utils.getWebText(url); 
            json = json.replace(/(<([^>]+)>)/ig, "");

            json_start_index = json.indexOf('[');
            json_end_index = json.lastIndexOf(']') + 1;
            
            var tmp = json.substring(json_start_index, json_end_index);
            var datas = JSON.parse(tmp);
                     
            subject_rand = subject_rand % datas.length;
            var keywordData = datas[subject_rand];
            var str = " - " + keywordData["subject"] + " (" + keywordData["genres"] + ")" + "\n   > 방영일 : " + keywordData["startDate"] + "\n   > " + keywordData["website"]; 
            
            if (subject_rand < (datas.length / 4)) {
               replier.reply("「이 작품이 정말 재밌습니다.」\n\n" + str);  
            }
            else if (subject_rand < (datas.length / 2)) {
               replier.reply("「저의 소중한 애니 하나를 추천드립니다.」\n\n" + str);  
            }
            else {
               replier.reply("「이런 애니는 어떠십니까?」\n\n" + str);  
            }
            
         } catch (e) {
            replier.reply("「고객님, 죄송하지만 잘 알아 듣지 못하였습니다.」");
         }

         return 0;
      }
   }

   return -1
}


function violet_command_response(msg, sender, isGroupChat, replier) {
   
   for (var i=0; i < violet_msg.length; i++) {
      if (msg.indexOf(violet_msg[i]) == 0) {
         if (help_response(msg, replier, help_msg) == 0) return 0;
         if (meet_response(msg, replier, meet_msg) == 0) return 0;
         if (cal_response(msg, replier, cal_msg) == 0) return 0;
         if (study_req_rsp(msg, isGroupChat, replier, study_msg) == 0) return 0;
         if (study_check_response(msg, replier, study_check_msg) == 0) return 0;
         if (study_del_response(msg, replier, study_del_msg) == 0) return 0;
         if (ani_quiz_response(msg, replier, ani_quiz_msg, ani_quiz_problem, ani_quiz_answer) == 0) return 0;
      }

      if (msg.indexOf(violet_msg[i]) != -1) {
         if (coin_response(msg, replier, coin_msg) == 0) return 0;
         if (nalssi_response(msg, replier, nalssi_msg) == 0) return 0;
         if (silsigan_response(msg, replier, silsigan_msg) == 0) return 0;
         if (today_ani_response(msg, replier, today_ani_msg) == 0) return 0;
         if (recommend_ani_response(msg, replier, recommend_ani_msg) == 0) return 0;
         if (ramen_response(msg, replier, ramen_msg, ramen_content, ramen_sub_content) == 0) return 0;
         if (basic_response(msg, replier, chik_msg, chik_reply) == 0) return 0;
         if (basic_response(msg, replier, violet_what_msg, violet_what_reply) == 0) return 0;
         if (basic_response(msg, replier, violet_love_msg, violet_love_reply) == 0) return 0;
         if (hogam_up_response(msg, replier, hogam_up_msg, sender) == 0) return 0;
         if (hogam_down_response(msg, replier, hogam_down_msg, sender) == 0) return 0;
         if (basic_response(msg, replier, meal_msg, meal_reply) == 0) return 0;
         if (lotto_response(msg, replier, lotto_msg) == 0) return 0;
      }
   }
   
   return -1;
}


function response(room, msg, sender, isGroupChat, replier, ImageDB) {
   var rand = Math.floor(Math.random() * RAND_MAX);
   var today = new Date();
   var time_flag = 0;

   /* 우선 반응 명령어 */
   if (yok_response(msg, replier, yok_msg) == 0) return;
   if (basic_response(msg, replier, violet_gil_msg, violet_gil_reply) == 0) return;

   /* 바이올렛 명령어 */
   if (violet_command_response(msg, sender, isGroupChat, replier) == 0) return;
   
   /* 기본적인 응답 */
   if (sometimes_basic_response(msg, replier, violet_msg, violet_reply) == 0) return;
   if (sometimes_basic_response(msg, replier, violet_love_msg, violet_love_reply) == 0) return;
   if (sometimes_basic_response(msg, replier, hello_msg, hello_reply) == 0) return;
   if (sometimes_basic_response(msg, replier, morning_msg, morning_reply) == 0) return;
   if (sometimes_basic_response(msg, replier, bye_msg, bye_reply) == 0) return;
   if (sometimes_basic_response(msg, replier, kkk_msg, kkk_reply) == 0) return;
   if (sometimes_basic_response(msg, replier, wow_msg, wow_reply) == 0) return;
   if (sometimes_basic_response(msg, replier, chool_msg, chool_reply) == 0) return;
   if (sometimes_basic_response(msg, replier, toi_msg, toi_reply) == 0) return;
   
   /* 이벤트성 응답 */
   if (ani_quiz_answer_response(msg, replier, ani_quiz_answer, sender) == 0) return;
   if (study_response(msg, replier, study_req, study_rsp) == 0) return;
}
