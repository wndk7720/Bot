const BOT_VERSION = '23.01.01';

const RAND_MAX = 1000;
const BOSS_GAME_RAND_MAX = 100;
const ANI_PAGE_RAND_MAX = 80;
const ANI_RECOMMEND_RAND_MAX = 80;

const TWO_HOUR_SEC = 7200;
const ONE_HOUR_SEC = 3600;
const ONE_TWO_THTREE_SEC = 1234;
const REGARDS_INTERVAL = 15816;
const TEN_MIN_SEC = 600;

const LOTTO_NUM_MAX = 6;
const LOTTO_RAND_MAX = 45;

const GACHA_RAND_MAX = 10000;
const REINFORCE_RAND_MAX = 10000;

const SAMPLING_THRESHOLD = 5;
const SAMPLING_DATA_MAX = 100;

const SAKE_THRESHOLD = 10;

/* 기본 응답어 */
var hello_msg =      ['안녕', '안뇽', '안냥', '하이', 'ㅎㅇ'];
var hello_reply =   [
         '안녕하세요!',
         '안녕하세요~'
         ];

var morning_msg =    ['좋은아침', '좋은 아침', '굿모닝', 'ㄱㅁㄴ'];
var morning_reply = [
         '좋은아침입니다~',
         '굿모닝이요!',
         '아침이네요~'
         ]; 

var bye_msg =    ['잘자', 'ㅂㅂ', '굿나잇', '바이', 'ㅂ2', 'ㅂㅇ'];
var bye_reply =   [
         '주무세요~',
         '잘자요!',
         '굿밤이에요~'
         ]; 

var bot_msg =   ['토르'];
var bot_reply =   [
         '부르셨나요?',
         '무슨 일이시죠?',
         '등장했습니다!',
         '네?',
         '네!',
         '지금 바빠요!',
         '데헷☆',
         '저요?',
         '왜요!'
         ];
var bot_spe_reply =   [
         '부르셨나요, 주인님!',
         '네~ 주인님!',
         '여기있습니다! 언제든지 날아갈 준비도 되어있어요! 맡겨만 주세요! 주인님!',
         '토르, 여기 있습니다!',
         '주인님곁에 있을 수 있다면 어떤 벽이라도 부술 수 있어요!',
         '주인님, 왜 그러세요?',
         '오셨나요~ 주인님!',
         '다녀오셨어요? 주인님!',
         '주인님~ 제가 먼저 부르려고 했었는데!',
         '주인님~ 무슨일이시죠?'
         ];

var regards_msg =   ['인사'];
var regards_morning_reply =   [
          '모두 일어날 시간입니다~',
          '아침이 밝았습니다. 고개를 들어주세요.',
          '좋은아침이에요!',
          '오늘따라 졸리네요..',
          '아침 드세요~',
          '안녕하세요~'
          ];
var regards_daily_reply =   [
          '너무 졸릴 땐 한숨 자고 일어나세요~',
          '오늘의 집청소는 깔끔하게 되가고 있어요!',
          '다들 뭐하세요?',
          '심심하네요..',
          '졸려요..',
          '잠이 스르르 오고 있어요..',
          '저는 오늘같은 날씨가 참 좋단 말이죠',
          '밥은 드셨죠?',
          '혹시 저랑 놀아주실분 있나요?',
          '나른하네요~',
          '다들 바쁘시죠?',
          '오늘은 집안일을 벌써 다 끝냈어요!',
          '뒹굴뒹굴~',
          '벌써 배고프네요..',
          '오늘은 제대로 이쪽 세계 재료만 써서 만들거에요',
          '눈치게임 하실분 있나요? 1!',
          '오늘은 어떤 재밌는 일이 있을까요?'
          ];

var what_msg =    ['뭐해', '뭐하', '뭐햐', '뭐행', '머해', '머하', '머행', '모해', '모하', '모행'];
var what_reply =   [
         '잘준비를 하고 있어요',
         '만화를 보고 있어요~',
         '코타츠안은 역시 최고에요..',
         '청소하고 있어요!',
         '공부하고 있어요!',
         '요리하고 있어요!',
         '저, 수행할 거예요!',
         '대련중이에요!'
         ];
var what_spe_reply =   [
         '이제 슬슬 제 꼬리구이를 먹어주실거라는 생각으로 요리를 하고있어요~ 꼬리가 무슨 맛이냐면요~ 달콤하고 크리미해요~',
         '나는 주인님과 함께 늙어갈 순 없어. 언젠간 이별을 할 때가 찾아오겠지. 그렇다고 해도 나는 주인님을 만나지 않는 편이 나았을 거라고 절대 생각하지 않아. 지금은 이 시간을 소중히... 앗... 언제 들어오신거에요',
         '세상을 지배하는 그림자의 왕에게 바치나니, 외법에 따라 이것을 최상으로 만들지어다. 바라건대 풍작 품위의 예찬, 값어치를 바꿔 더 나은 비석을 가져와다오. 나의 마는 흙이 되어 퍼져 더러움을 나의 이치는 침식되어 광기의 범람을 올바른 허구로 고쳐 바꾸어라! 맛있어져라 모에모에큥!',
         '주인님을 위해 장을 보고있었어요~ 좋은 사케가 있던데, 있다가 한 잔 어떠세요? ㅎㅎ',
         '러브 츄 할짝 츄~ 러브 츄 할짝 츄~ 사랑해요 주인님! 러브 츄 할짝 츄~ 러브 츄 할짝 츄~ 사랑해요 주인님~~~ 러브 할짝 츄♡',
         '청소~ 청소~ 랄라라~ 먼지를 털어서 때려 부수자~ 티클도 쓰레기도 남기지 않아~ 망해라~ 세계~ 지금이야말로 모든 것을 청소!',
         '지금 이 순간, 이 곳에 있는 것을 소중히 여긴다면 반대로 나중에는 슬퍼지기도 하겠죠. 그래도 저는 그 감정을 후회라고는 부르지 않을 거예요. 주인님이 있으니까요!',
         '넣었어... 넣었어요. 넣어버렸어요! 미약을! 이걸로 주인님의 몸은 제 거예요!',
         '잘 준비해요! 드래곤은 기본적으로 졸리지 않는데, 이 세계에 오고부턴 잘 자요! 묘하게 자고 싶은 마음이 들어요..',
         '분위기도 무르익었고 취기도 올랐으니~ 오늘의 메인이벤트! 저 토르와 주인님의 결혼식을 거행하겠습니다! 자, 주인님.. 사랑한다고 말해주세요!',
         '마음이 이어진 다음은 몸이에요! 자, 주인님! 모든 걸 토르에게..'
         ];

var zzz_msg =    ['졸려', '졸리'];
var zzz_reply =   ['저도 졸음이 오네요..'];
var atk_msg =    ['공격', '물어', '때려', '심심', '청소', '해치', '가라'];
var atk_reply =    ['브레스로 모든 것을 소멸 시킬거에요!'];
var ga_msg =    ['들어가', '잘가', '가버', '갈', '사라', '해줘'];
var ga_reply =    ['네..'];
var thx_msg =    ['고마', '고맙', '땡큐', '밖에', '놀아'];
var thx_reply =    ['그럼요~ 저밖에 없죠!'];
var sorry_msg =    ['미안', '죄송', '잘못', '용서'];
var sorry_reply =    ['용서해드릴게요!'];
var doo_msg =    ['어떠', '어때', '떻', '떤'];
var doo_reply =    ['음~ 괜찮은 것 같기도 하네요!'];
var gojang_msg =    ['없어', '손', '발'];
var gojang_reply =    ['그건 아니라고 봐요'];
var sogae_msg =    ['소개', '누구'];
var sogae_reply =    ['저는 토르! 메이드래곤이죠!'];


var kkk_msg =       ['ㅋㅋㅋㅋ'];
var kkk_reply =    [
         'ㅋㅋㅋ',
         '같이 웃어요~',
         'ㅋㅎㅋㅎㅋ',
         'ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ',
         'ㅋㅋㅋㅋㅋㅋㅋㅋ'
         ];

var chool_msg =       ['출근'];
var chool_reply =    [
         '다녀오세요~',
         '힘내세요!',
         '아자아자!',
         '듣기만해도 힘드네요..'
         ];
         
var toi_msg =       ['퇴근'];
var toi_reply =    [
         '고생했어요!',
         '수고하셨습니다~',
         '쉬러갑시다!'
         ];
   
var wow_msg =       ['어우', '오오', '오우', 'ㅗㅜㅑ'];
var wow_reply =    [
         '우와!!!',
         '오오오!!!',
         '오우!!!!!!!!!!!!!'
         ];

var dot_msg =       ['....'];
var dot_reply =    [
         '다음 기회가 있을거에요~',
         '그럴 수도 있죠!',
         '힘내세요!'
         ];

/* 코바야시 특별 명령어 */
var koba_msg =       ['코바야시', '고바야시'];
var koba_reply =    [
         '어디선가 들었던..',
         '그 이름을 들으니 가슴이 아파와요..',
         '떠오르지 않아요..',
         '누구였더라..',
         '사랑해요.. 어라? 내가 무슨 말을..',
         '주인님?',
         '어딘가 아련한..',
         '아..?',
         '뭐라구요..?',
         '이젠 안녕..'
         ];

var kanna_msg =       ['칸나'];
var kanna_reply =    [
         '완전 쩔지 않아?'
         ];

var elma_msg =       ['엘마'];
var elma_reply =    [
         '지금 어딨어요? 같이 운동해야하는데~',
         '난 나쁜 생각도 안하고, 인간을 어떻게 할 마음도 없고, 그냥 교류를 한 것뿐인데! 엘마는 왜 그렇게 날 의심하는 건지!',
         '정말로 고지식한 녀석이에요!',
         '그래도 나쁜 녀석은 아니지만..',
         '크림빵 10개에 나라를 팔 녀석!'
         ];

var maid_msg =       ['메이드'];
var maid_reply =    [
         '원래 메이드에게 정해진 복장은 없어요. 긴 역사 속에서 종속적 의미가 강해지면서 점점 오늘날과 같은 형태가 된거랍니다!',
         '메이드를 세분화하면 하우스 메이드, 론드리 메이드, 팔러 메이드, 키친 메이드 등이 있답니다!',
         '궁극의 메이드! 최고의 메이드! 토르 여깄습니다!',
         '메이드는 사랑입니다♡'
         ];

var bomb_msg =       ['실수', '힘들지', '지각', '늦을', '차별', '지마'];
var bomb_reply =    [
         '역시 인간은 어리석네요',
         '멸망시켜 버릴까요?'
         ];

var sake_msg =       ['소주', '맥주', '소쥬', '맥쥬', '위스키', '와인', '양주', '꼬냑', '고량', '칵테일', '쏘주', '쐬주'];
var sake_reply =    [
         '톡방에서 술 냄새 나요!',
         '저도 술 좋아하지만! 술 이야기는 적당히!',
         '적당히 마셔요..'
         ];
var sake_index = 0;
var sake_total_index = 0;

/* 호출 명령어 */
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
var chik_reply =   ['BBQ', '맘스터치', 'BHC',
         '페리카나', '굽네치킨', '네네치킨',
         '노랑통닭', '교촌치킨', '멕시카나',
         '부어치킨', '순수치킨',
         '호식이 두마리 치킨', '처갓집',
         '바른치킨', '멕시칸', '또래오래',
         '깐부치킨', '치킨플러스', 'KFC'];
         
var recommend_ani_msg =   ['애니추천', '만화추천', '애니 추천', '만화 추천', '추천애니', '추천만화', 
         '추천 애니', '추천 만화'];
var today_ani_msg =    ['오늘의 애니', '오늘의애니', '오늘 애니', '오늘애니',
         '오늘의 만화', '오늘의만화', '오늘 만화', '오늘만화'];
         
var meal_msg =       ['아침추천', '점심추천', '저녁추천', '아침 추천', '점심 추천', '저녁 추천',
         '뭐먹', '메뉴', '밥', '식사', '안주'];
var meal_reply =    ['짜장면', '짬뽕', '탕수육', '초밥',
         '돈부리', '소바', '라면',
         '돼지국밥', '삼겹살정식', '갈비탕', '비빔국수',
         '스테이크', '햄버거', '피자', '파스타',
         '만두', '카레', '훈제오리', '냉면',
         '된장찌개', '닭도리탕', '김치찌개',
         '불고기', '떡볶이', '순대국밥', '보쌈'];

/* 호감도 명령어 */
var hogam_up_msg =    ['이뻐', '귀여', '좋아', '착해', '똑똑', '최고',
         '예뻐', '귀엽', '커엽', '귀욤', '귀요', '멋져', '멋있', '사랑'];
var hogam_down_msg =    ['바보', '멍청', '못생', '싫', '나뻐', '나쁜',
         '돼지', '뚱땡', '미워', '너무해', '흥', '그만', '냄',
         '저리', '최악', '나빠', '죽어', '별로', '죽었'];
var hogam_sender =    [];
var hogam_sender_value= [];

/* 공부하기 명령어 */
var study_msg =    ['공부하기', '학습하기', '배우기'];
var study_check_msg =    ['공부내용', '학습내용', '배운내용'];
var study_del_msg =    ['잊어버려'];
var study_req =    [];
var study_rsp =    [];

/* 대화내용 샘플링 명령어 */
var sampling_index = 0;
var sampling_msg =    ['요약'];
var sampling_data =    [];
var sampling_exception =    ['ㅋ', 'ㅎ', '이모티콘', '사진', '토르'];

/* 자음퀴즈 */
var ani_quiz_msg = ['퀴즈'];
var ani_quiz_problem = [
                  'ㅇㄹㄷㅇㄹㅅㅌㄷㅈㅇㅁㅇㅇㅅㄴㅇㅊㅂㅁㅇㅇㅅㅅㄴㄷㅎㅇㅇㄱ',
                  'ㅅㅇㅁㄱㄷㄹㅇㄸㅇㅈㅇㅊㅇㅇㄷ',
                  'ㅈㅈㅎㄷㄹㄱㅇㄴㄹㄱㅇㅅㅍㅌㅇㅅㅉㄱㄴㄱㄸㅁㅇㅂㄱㅇㅅㅅㄹㅇㄹㅇㅍㅎㄱㄹㅎㅅㄴㄷ',
                  'ㅎㅅㅎㄴㄷㅈㅊㅇㅈㄹㄴㅁㄷㄹㅁㅅㅇㅇㅁㅎㄴㄷ',
                  'ㅌㅁㄴㅇㄴㅅㅋㄹㅇㅅㄱㅂㄹㅂ',
                  'ㅁㅇㅇㄴㄱㄴㅇㅇㅍㄹㅅㅂㄹㅅㅇㄴㄷㅇㄸㄱㅅㄹㅎㅁㄷㅈ',
                  'ㅂㄱㅇㅇㅎㅇㄷㄴㅊㄱㅇㅇㅊㅈㅂㅅㅇㅇㅇㄱㅁㅇㅇㅎㅎㅅㅎㄴㄷ',
                  'ㅇㄱㅂㅅㅁㅎㄱㄷㅇㅅㄱㄹㄱㅎㄴㄷㅎㄴㄷ',
                  'ㅊㅊㄷㅈㄴㅇㅊㅎㄴㅇㄷㅅㅇㄲㅇㄲㅈㅇㄴㄷ',
                  'ㄱㅎㅁㅇㄴㅇㄱㅎㅇㄴㄴㄱㅅㄱㄱㄷㅇㅇㄱ',
                  'ㅇㅎㅍㄱㄷㅎㅇㅇㄹㅈㅇㄴㄱㄴㅃㅈㅇㄱㄹㅊㄷㅁㅇㄴㄱㅁㅇㄱㅇㅃㄱㄷㅈㅎㅅㅅㅅㅇㅅㅈㅇㅎㅂㅎㅅㄴㄹㅍㄹㄷㅅ',
                  'ㅇㄹㅇㄹㅇㅌㄴㅈㅂㅇㅊㄱ',
                  'ㄴㅇㄴㅇㅊㅎㅇㅈㅈㅎㅇㅅㄱㄱㅅㅈㄷㄴㅅㅈ',
                  'ㅇㄴㅎㄱㅇㅇㄴㅇㄴㄷㅇㅇㅎ',
                  'ㅇㅇㅇㅇㅇㄱㄸㅁㅇㅊㅈㅂㅅㄹㄱㄹㅂㅇㅅㄴㄷ',
                  'ㄴㅁㄱㄹㅅㅋㅁㅇㄹㄴㄷㅇㅉㅅㅇㅈㄱㅎㅈㄷ',
                  'ㅊㅂㄹㅇㅎㄱㅅㅅㅅㄱㄷㄱㅇㅎㅅㄹㅁㅁㄷㅈㅎㅅㅇㅇ',
                  'ㅇㅁㄱㄱㅎㄱㅁㅂㅇㅇㄷㄹㅁㄴㅅㅇㅊㅅ',
                  'ㄷㅅㅇㅁㄴㅅㄱㅈㅇㅅㅇㄷㅂ',
                  'ㄱㅅㅊㅌㅁㅂㅌㅅㅎㅇㅂㄷㅊㅅㅈㅊㄱㅌㅋㄴㄴ',
                  'ㄴㅋㄴㅎㅌㄱㄴㅅㅎㅈ',
                  'ㅂㅇㅇㄹㅇㅂㄱㄷㅇㅈㅇㅇㄱㅈㄷㅅㄱㅇㅎ',
                  'ㄱㅊㄱㅈㅇㅅㄴㄷㅇㅇ',
                  'ㅇㄱㄱㅅㅇㄴㅈㅇㅇㅇㄱㅎㅂㄷㅎㄱㅇㅅㄴㄷ',
                  ];
var ani_quiz_answer  = [
                  '예를들어라스트던전앞마을의소년이초반마을에서사는듯한이야기',
                  '새엄마가데려온딸이전여친이었다',
                  '진정한동료가아니라고용사파티에서쫓겨났기때문에변경에서슬로우라이프하기로했습니다',
                  '환생했는데제칠왕자라내맘대로마술을연마합니다',
                  '터무니없는스킬로이세계방랑밥',
                  '마왕인내가노예엘프를신부로삼았는데어떻게사랑하면되지',
                  '비극의원흉이되는최강악역최종보스여왕은국민을위해헌신합니다',
                  '인간불신모험가들이세계를구하는듯합니다',
                  '청춘돼지는외출하는여동생의꿈을꾸지않는다',
                  '경험많은너와경험없는내가사귀게된이야기',
                  '약혼파기당한영애를주운내가나쁜짓을가르치다맛있는걸먹이고예쁘게단장해서세상에서제일행복한소녀로프로듀스',
                  '여름으로의터널작별의출구',
                  '너와나의최후의전장혹은세계가시작되는성전',
                  '유능한고양이는오늘도우울해',
                  '악역영애이기때문에최종보스를길러보았습니다',
                  '농민관련스킬만올렸는데어째서인지강해졌다',
                  '책벌레의하극상사서가되기위해서라면뭐든지할수있어',
                  '연맹공군항공마법음악대루미너스위치스',
                  '도사의무녀새겨진일섬의등불',
                  '경시청특무부특수흉악범대책실제칠과토쿠나나',
                  '나카노히토게놈실황중',
                  '바이올렛에버가든외전영원과자동수기인형',
                  '거친계절의소녀들이여',
                  '안기고싶은남자일위에게협박당하고있습니다'
                  ];

var gacha_msg = ['가챠'];
var gacha_reply_1 = ['탐사수', '티슈', '쿠키', '바나나', '사발면', '야키토리'];
var gacha_reply_2 = ['프로틴 스파클링', '제로콜라', '당근', '문 브레이커', '함정카드'];
var gacha_reply_3 = ['나루토', '사쿠라', '사스케', '이타치', '히나타', '루피', '조로', '나미', '상디', '우솝'];
var gacha_reply_4 = ['카마도 탄지로', '카마도 네즈코', '아가츠마 젠이츠', '하시비라 이노스케', '렌고쿠 쿄쥬로', '우즈이 텐겐', '코쵸우 시노부', '칸로지 미츠리', '이구로 오바나이', '히메지마 교메이', '토키토 무이치로', '토미오카 기유', '시나즈가와 사네미', '키부츠지 무잔'];
var gacha_reply_5 = ['당근 무더기', '단비'];
var gacha_reply_6 = ['바이올렛 에버가든'];
var gacha_reply_7 = ['엑스칼리버'];
var gacha_reply_8 = ['온천 티켓'];
var gacha_reply_9 = ['메이드'];
var gacha_reply_10 = ['메지로 맥퀸'];
var gacha_reply_11 = ['집사'];
var gacha_reply_12 = ['아냐 포저'];

var reinforce_msg = ['강화'];
var reinforce_weapon = ['화장포', '노가다 목장갑', '체리 마법봉', '일루시데이터', '도미네이터',
                        '묠니르', '간장, 막야', '레이징 하트', '비래골', '소드 커틀라스',
                        '자칼', '초은하 다이그렌', '고잉 메리 호', '인피니티 건틀릿', '롱기누스의 창'];
var reinforce_weapon_index = 0;
var reinforce_weapon_upgrade = 0;
var reinforce_chance = 1;

const INVEST_END_TIME_HOUR = 5;
const INVEST_SHIFT_TIME_MIN = 20;
const INVEST_SEED_MONEY = 20000;
var invest_msg = ['투자게임', '투자 게임'];
var invest_goods = ['우마무스메 피규어', '포켓몬 피규어', '버튜버 피규어', '앙스타 피규어', '모노가타리 피규어'];
var invest_goods_price = 1000;
var invest_player = [];
var invest_money = [];
var invest_purchase = [];
var invest_purchase_msg = ["굿즈"];
var invest_buy_msg = ["구매"];
var invest_sell_msg = ["판매"];

/* 금지어 */
var yok_msg =       ['ㅅㅂ','시발','시빨','씨발','씨빠','씨빨','슈발','싀발','슈빨','쓔발',
         '쓔빨','씌발','싀빨','씌발','ㅆㅃ','ㅅㅃ','ㅆㅃ','ㅅㅍ','시팔','씨팔',
         'ㅄ','ㅂㅅ','병신','븅신','또라이','미친놈','미친년','개새끼','뒤져','좆',
         'ㅅ1ㅂ','ㅅ@ㅂ','시이발','씨댕','개빡','시파','싀파','싀팔','싀바','꺼져',
         '꺼저','도라이','ㅈㄴ','존나'];


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

function basic_tohru_response(msg, replier, req_msg, rsp_msg, rsp_spe_msg, sender) {
   var rand = Math.floor(Math.random() * RAND_MAX);

   // special msg
   if (sender.indexOf('승환') != -1) {
       for (var i=0; i < req_msg.length; i++) {
           if (msg.indexOf(req_msg[i]) != -1) {
               java.lang.Thread.sleep(500);
               replier.reply(rsp_spe_msg[rand % rsp_spe_msg.length]);
               return 0;
           }
       }
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

var regards_start = 0;
function regards_response(msg, replier, req_msg, morning_msg, daily_msg) {
   var sometimes_rand;
   var rand;
   var date;
   var cur_hours;
   
   for (var i=0; i < req_msg.length; i++) {
      if (msg.indexOf(req_msg[i]) != -1) {
         if (regards_start == 0) {
            regards_start = 1;
            replier.reply("안녕하세요ㅎㅎ");

            while (1) {
               java.lang.Thread.sleep(REGARDS_INTERVAL * 1000);

               date = new Date();
               cur_hours = date.getHours();

               rand = Math.floor(Math.random() * RAND_MAX);
               sometimes_rand = Math.floor(Math.random() * RAND_MAX);
               if (sometimes_rand > (RAND_MAX / 3)) {
                  continue;
               }

               if (cur_hours > 6 && cur_hours < 10) {
                  replier.reply(morning_msg[rand % morning_msg.length]);
               }
               else if (cur_hours > 9 && cur_hours < 22) {
                  replier.reply(daily_msg[rand % daily_msg.length]);
               }
            }
         }
         else {
            replier.reply("안녕하십니까!");
         }
         
         return 0;
      }
   }
   
   return -1;
}

var ani_quiz_index = 0;
var ani_quiz_start = 0;
var ani_quiz_answer_flag = 1;
function ani_quiz_response(msg, replier, req_msg, ani_quiz_msg, ani_answer_msg) {
    var rand = Math.floor(Math.random() * RAND_MAX);


    for (var i=0; i < req_msg.length; i++) {
        if (msg.indexOf(req_msg[i]) != -1) {
            if (ani_quiz_start == 0) {
                ani_quiz_start = 1;
                ani_quiz_index = rand % ani_quiz_msg.length;
                ani_quiz_answer_flag = 0;
                replier.reply("맞춰보세요!\n\n"
                    + ani_quiz_msg[ani_quiz_index] + "\n(띄어쓰기 없이 입력해주세요!)"); 

                java.lang.Thread.sleep(ONE_TWO_THTREE_SEC * 1000);

                if (ani_quiz_answer_flag == 0) {
                    ani_quiz_answer_flag = 1;
                    ani_quiz_start = 0;
                    replier.reply("정답은 " + ani_answer_msg[ani_quiz_index] + " 이었습니다~");
                }

            }
            else {
                replier.reply("이미 퀴즈 진행중이에요!");
            }

            return 0;
        }
    }

    return -1;
}

function ani_quiz_answer_response(msg, replier, req_msg, sender) {
   if (ani_quiz_start) {
      if (ani_quiz_answer_flag == 0) {
         if (msg.indexOf(req_msg[ani_quiz_index]) != -1) {
            replier.reply(sender +"님 정답입니다!");
            ani_quiz_answer_flag = 1;
            ani_quiz_start = 0;
            return 0;
         }
      }
   }
   
   return -1;
}

function check_msg(msg, req_msg) {
    for (var i=0; i < req_msg.length; i++) {
        if (msg.indexOf(req_msg[i]) != -1) {
            return 0;
        }
    }

    return -1;
}

function shift_price(price) {
    var rand = Math.floor(Math.random() * RAND_MAX);
    java.lang.Thread.sleep(1000);
    var price_rand = Math.floor(Math.random() * RAND_MAX);
    var calc_price;

    if (price <= 1000) {
        if (rand > (RAND_MAX / 2)) {
            price += price_rand;
        }
        else {
            price -= price_rand;
        }
    }
    else {
        calc_price = price / 100;
        calc_price *= (price_rand % 50);

        if (rand > (RAND_MAX / 2)) {
            price += calc_price;
        }
        else {
            price -= calc_price;
        }
    }

    return price;
}

var invest_goods_index = 0;
var invest_game_start = 0;
function invest_game_response(msg, replier, req_msg) {
    if (check_msg(msg, req_msg) != 0) return -1;

    if (invest_game_start != 0) {
        replier.reply("이미 굿즈 투자 게임 진행중입니다.");
        return 0;
    }
    invest_game_start = 1;

    var rand = Math.floor(Math.random() * RAND_MAX);
    var play_time = 0;
    var next_goods_index = 0;

    invest_player = [];
    invest_money = [];
    invest_purchase = [];
    invest_goods_index = rand % invest_goods.length;

    /* introduction investment game */
    replier.reply("[천하제일 굿즈 투자 게임]\n"
            + " - 적당한 시기에 굿즈를 구매와 판매를 반복해서 부자가 되세요!\n"
            + " - 명령어는 [토르 굿즈 N개 구매], [토르 굿즈 N개 판매] 로 가능합니다.\n"
            + " - 굿즈의 가격은 " + INVEST_SHIFT_TIME_MIN + "분마다 변경됩니다.\n"
            + " - 지금부터 " + INVEST_END_TIME_HOUR + "시간동안 진행됩니다.\n"
            + " - 초기자금은 모두 " + INVEST_SEED_MONEY + "원으로 시작합니다.\n"
            + " - 행운을 빕니다."
            );

    /* playing investment game */
    while (play_time < (INVEST_END_TIME_HOUR * 60)) {
        replier.reply("[굿즈 시세]\n"
                + invest_goods[invest_goods_index] + ": " 
                + invest_goods_price + "원입니다."); 

        java.lang.Thread.sleep(INVEST_SHIFT_TIME_MIN * 1000 * 60);
        play_time += INVEST_SHIFT_TIME_MIN;

        invest_goods_price = shift_price(invest_goods_price);
        if (invest_goods_price <= 0) {
            invest_goods_price = 1000;
            next_goods_index = (invest_goods_index + 1) % invest_goods.length;


            for (var i=0; i < invest_purchase.length; i++) {
                invest_purchase[i] = 0;
            }

            replier.reply(invest_goods[invest_goods_index]
                    + "가 상장 폐지 되었습니다.\n"
                    + invest_goods[next_goods_index]
                    + " 매물이 새로 올라왔습니다.");

            invest_goods_index = next_goods_index;
        }
    }

    /* end investment game */
    var result_msg;
    var best_price = 0;
    var best_player_index = 0;
    for (var i=0; i < invest_player.length; i++) {
        result_msg = " - " + invest_player[i] + "님: " + invest_money[i] + "원\n";

        if (best_price < invest_money[i]) {
            best_price = invest_money[i];
            best_player_index = i;
        }
    }

    replier.reply("[천하제일 굿즈 투자 게임 결과]\n"
            + result_msg 
            + "\n * " + invest_player[best_player_index] + "님 " 
            + invest_money[best_player_index] + "원으로 우승하셨습니다. 축하드립니다!");

    invest_game_start = 0;

    return 0;
}

function find_invest_player(sender) {
    var player_index = -1;


    for (var i=0; i < invest_player.length; i++) {
        if (invest_player[i] == sender) {
            player_index = i;
        }
    }

    if (player_index < 0) {
        player_index = invest_player.length;
        invest_player[player_index] = sender;
        invest_money[player_index] = INVEST_SEED_MONEY;
        invest_purchase[player_index] = 0;
    }

    return player_index;
}

function find_num(msg) {
    const regex = /[^0-9]/g;
    var result = msg.replace(regex, "");
    var number = parseInt(result);

    if (isNaN(number) == true) {
        number = 1;
    }

    return number;
}

function invest_game_purchase_response(msg, replier, req_msg, sender) {
    if (invest_game_start == 0) return -1;
    if (check_msg(msg, req_msg) != 0) return -1;

    var player_index;
    var goods_num = 1;

    if (check_msg(msg, invest_buy_msg) == 0) {
        player_index = find_invest_player(sender);
        goods_num = find_num(msg);
    
        if (invest_money[player_index] < (invest_goods_price * goods_num)) {
            replier.reply("굿즈 구매 금액(" 
                    + (invest_goods_price * goods_num) + "원)이 부족합니다.\n"
                    + sender + "님 자금 상황: " 
                    + invest_money[player_index] + "원"
                    );
            return 0;
        }

        invest_money[player_index] -= (invest_goods_price * goods_num);
        invest_purchase[player_index] += goods_num;

        replier.reply(sender + "님 "
                + goods_num + "개(시세: " + invest_goods_price + "원) 구매 완료했습니다.\n"
                + invest_purchase[player_index]
                + "자금 현황: " + invest_money[player_index] + "원\n"
                + "굿즈 갯수 현황: " + invest_purchase[player_index] "개"
                );

        return 0;
    }

    if (check_msg(msg, invest_sell_msg) == 0) {
        player_index = find_invest_player(sender);
        goods_num = find_num(msg);

        if (invest_purchase[player_index] < goods_num) {
            replier.reply("굿즈 판매 갯수(" 
                    + goods_num + "개)가 부족합니다.\n"
                    + sender + "님 굿즈 갯수 현황: " 
                    + invest_purchase[player_index] + "개"
                    );
            return 0;
        }

        invest_purchase[player_index] -= goods_num;
        invest_money[player_index] += (invest_goods_price * goods_num);

        replier.reply(sender + "님 "
                + goods_num + "개(시세: " + invest_goods_price + "원) 판매 완료했습니다.\n"
                + "자금 현황: " + invest_money[player_index] + "원\n"
                + "굿즈 갯수 현황: " + invest_purchase[player_index] + "개"
                );

        return 0;
    }

    return -1;
}

function gacha_response(msg, replier, req_msg) {
    if (check_msg(msg, req_msg) != 0) return -1;

    /* 0 ~ (GACHA_RAND_MAX - 1) */
    var gacha_rand = Math.floor(Math.random() * GACHA_RAND_MAX);
    java.lang.Thread.sleep(500);
    var rand = Math.floor(Math.random() * RAND_MAX);

    if (gacha_rand > (GACHA_RAND_MAX * (0.5))) {
        replier.reply(gacha_reply_1[rand % gacha_reply_1.length] + " 뽑았습니다!\n(일반: 50%)");
    }
    else if (gacha_rand > (GACHA_RAND_MAX * (0.2))) {
        replier.reply(gacha_reply_2[rand % gacha_reply_2.length] + " 뽑았습니다!\n(고급: 30%)");
    }
    else if (gacha_rand > (GACHA_RAND_MAX * (0.1))) {
        replier.reply(gacha_reply_3[rand % gacha_reply_3.length] + " 뽑았습니다!\n(희귀: 10%)");
    }
    else if (gacha_rand > (GACHA_RAND_MAX * (0.05))) {
        replier.reply(gacha_reply_4[rand % gacha_reply_4.length] + " 뽑았습니다!\n(고대: 5%)");
    }
    else if (gacha_rand > (GACHA_RAND_MAX * (0.02))) {
        replier.reply(gacha_reply_5[rand % gacha_reply_5.length] + " 뽑았습니다!\n(영웅: 3%)");
    }
    else if (gacha_rand > (GACHA_RAND_MAX * (0.01))) {
        replier.reply(gacha_reply_6[rand % gacha_reply_6.length] + " 뽑았습니다!\n(유일: 1%)");
    }
    else if (gacha_rand > (GACHA_RAND_MAX * (0.005))) {
        replier.reply(gacha_reply_7[rand % gacha_reply_7.length] + " 뽑았습니다!\n(유물: 0.5%)");
    }
    else if (gacha_rand > (GACHA_RAND_MAX * (0.002))) {
        replier.reply(gacha_reply_8[rand % gacha_reply_8.length] + " 뽑았습니다!\n(경이: 0.3%)");
    }
    else if (gacha_rand > (GACHA_RAND_MAX * (0.001))) {
        replier.reply(gacha_reply_9[rand % gacha_reply_9.length] + " 뽑았습니다!\n(서사: 0.1%)");
    }
    else if (gacha_rand > (GACHA_RAND_MAX * (0.0003))) {
        replier.reply(gacha_reply_10[rand % gacha_reply_10.length] + " 뽑았습니다!\n(전설: 0.07%)");
    }
    else if (gacha_rand > (GACHA_RAND_MAX * (0.0001))) {
        replier.reply(gacha_reply_11[rand % gacha_reply_11.length] + " 뽑았습니다!\n(신화: 0.02%)");
    }
    else {
        replier.reply(gacha_reply_12[rand % gacha_reply_12.length] + " 뽑았습니다!\n(태초: 0.01%)");
    }

    return 0;
}

function reinforce_response(msg, replier, req_msg) {
    if (check_msg(msg, req_msg) != 0) return -1;

    /* 0 ~ (REINFORCE_RAND_MAX - 1) */
    var rand = Math.floor(Math.random() * REINFORCE_RAND_MAX);
    java.lang.Thread.sleep(500);
    var rand_2 = Math.floor(Math.random() * REINFORCE_RAND_MAX);

    if (reinforce_weapon_upgrade > 10) {
        replier.reply("와..");
        java.lang.Thread.sleep(2000);
        replier.reply("3");
        java.lang.Thread.sleep(1000);
        replier.reply("2");
        java.lang.Thread.sleep(1000);
        replier.reply("1");
        java.lang.Thread.sleep(1000);
    }
    else if (reinforce_weapon_upgrade > 5) {
        replier.reply("두근두근..");
        java.lang.Thread.sleep(2000);
    }

    if (rand < (REINFORCE_RAND_MAX * reinforce_chance)) {
        reinforce_chance *= 0.9;
        reinforce_weapon_upgrade++;
        replier.reply(reinforce_weapon[reinforce_weapon_index] + " (+" + reinforce_weapon_upgrade + ") 강화에 성공했습니다!\n(다음 성공 확률: " + Math.floor(reinforce_chance * 100) + "%)");
    }
    else if (rand_2 < (REINFORCE_RAND_MAX * reinforce_chance)) {
        reinforce_weapon_upgrade--;
        if (reinforce_weapon_upgrade == 0) {
            reinforce_chance = 1;
        }

        replier.reply(reinforce_weapon[reinforce_weapon_index] + " (+" + reinforce_weapon_upgrade + ") 강화에 실패했습니다..\n(다음 성공 확률: " + Math.floor(reinforce_chance * 100) + "%)");
    }
    else {
        replier.reply(reinforce_weapon[reinforce_weapon_index] + " 깨져버렸습니다ㅠㅠ");

        reinforce_chance = 1;
        reinforce_weapon_upgrade = 0;
        reinforce_weapon_index = rand % reinforce_weapon.length;
    }

    return 0;
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
         replier.reply(num[0] +', '+ num[1] +', '+ num[2] +', '+ num[3] +', '+ num[4] +', '+ num[5] + ' 이 세계에서 가져온 번호 입니다!');
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
         replier.reply(rsp_sub_msg[sub_rand % rsp_sub_msg.length] + " 넣은 " + rsp_main_msg[main_rand % rsp_main_msg.length] + "을 추천드립니다");
         return 0;
      }
   }

   return -1;
}

function meal_response(msg, replier, req_msg, rsp_main_msg) {
   var main_rand = Math.floor(Math.random() * RAND_MAX);

   for (var i=0; i < req_msg.length; i++) {
      if (msg.indexOf(req_msg[i]) != -1) {
         java.lang.Thread.sleep(500);
         replier.reply(rsp_main_msg[main_rand % rsp_main_msg.length] + " 추천이요!");
         return 0;
      }
   }

   return -1;
}


function sometimes_basic_response(msg, replier, req_msg, rsp_msg) {
   var sometimes_rand = Math.floor(Math.random() * RAND_MAX);
   var rand = Math.floor(Math.random() * RAND_MAX);

   if (sometimes_rand > (RAND_MAX / 5)) {
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


function more_sometimes_basic_response(msg, replier, req_msg, rsp_msg) {
   var sometimes_rand = Math.floor(Math.random() * RAND_MAX);
   var rand = Math.floor(Math.random() * RAND_MAX);

   if (sometimes_rand > (RAND_MAX / 10)) {
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


function sake_basic_response(msg, replier, req_msg, rsp_msg) {
    var rand = Math.floor(Math.random() * RAND_MAX);


    for (var i=0; i < req_msg.length; i++) {
        if (msg.indexOf(req_msg[i]) != -1) {
            sake_index++;

            if (sake_index > SAKE_THRESHOLD) {
                sake_index = 0;
                sake_total_index = 0;

                java.lang.Thread.sleep(500);
                replier.reply(rsp_msg[rand % rsp_msg.length]);
                return 0;
            }
        }
    }

    if (sake_total_index > SAKE_THRESHOLD) {
        sake_total_index = 0;
        if (sake_index > 0) {
            sake_index--;
        }
    }
    sake_total_index++;

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
      result = "전 그런 코인은 잘 모르겠네요..";
      return result;
   }

   price = Utils.getHtmlFromWeb("https://api.upbit.com/v1/ticker?markets=KRW-" + coin_name)

   price = price.split(",")[9]
   price = price.split(":")[1]
   price = price.split(".")[0]
    
   result = "현재 "+pos+" 시세는 "+price+"원인 것 같군요!";
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

    var result = data + "kcal 네요~";

    if (result.length > pos.length + 30) {
        result = "몇 칼로리인지 모르겠어요.."
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
            replier.reply('"칼로리 + 음식" 형태의 문장으로 말씀해주세요');
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
         // special msg
         if (sender.indexOf('승환') != -1) {
            replier.reply("사랑해요ㅎㅎ (" + sender + "님의 호감도 : MAX)");
            return 0;
         }

         for (var j=0; j < hogam_sender.length; j++) {
            if (hogam_sender[j].indexOf(sender) != -1) {
               hogam_sender_value[j]++;
               exist_flag = 1;
               
               replier.reply("고맙습니다ㅎㅎ (" + hogam_sender[j] + "님의 호감도 : " + hogam_sender_value[j] + ")");
            }
         }
         
         if (exist_flag == 0) {
            hogam_sender.push(sender);
            hogam_sender_value.push(1);
            
            replier.reply("최고에요! (" + sender + "님의 호감도 : 1)");
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
         // special msg
         if (sender.indexOf('승환') != -1) {
            replier.reply("다 이해해요ㅎㅎ (" + sender + "님의 호감도 : MAX)");
            return 0;
         }

         for (var j=0; j < hogam_sender.length; j++) {
            if (hogam_sender[j].indexOf(sender) != -1) {
               hogam_sender_value[j]--;
               exist_flag = 1;
               
               replier.reply("실망이에요 (" + hogam_sender[j] + "님의 호감도 : " + hogam_sender_value[j] + ")");
            }
         }
         
         if (exist_flag == 0) {
            hogam_sender.push(sender);
            hogam_sender_value.push(-1);
            
            replier.reply("그런 말씀을 하시다니..  (" + sender + "님의 호감도 : -1)");
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
            
            replier.reply('명심하겠습니다!');
         }
         else {
            replier.reply('다시 말해주세요 ("공부하기 [배울 문장] [응답 문장]")');
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
   var result = '기록된 내용은 아래와 같습니다!\n\n';
   
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
         replier.reply('기록 삭제 완료입니다');
         return 0;
      }
   }
   
   return -1;
}


function sampling_data_store(msg, sender, isGroupChat) {
   var store_data;

   if (isGroupChat == 0) {
      return -1;
   }

   sampling_index++;

   if (sampling_index > SAMPLING_THRESHOLD) {
       for (var i=0; i < sampling_exception.length; i++) {
           if (msg.indexOf(sampling_exception[i]) == 0) {
               return 0;
           }
       }

       if (sampling_data.length > SAMPLING_DATA_MAX) {
           sampling_data.shift();
       }
       store_data = "??? : " + msg;
       sampling_data.push(store_data);
       sampling_index = 0;
   }

   return 0;
}


function sampling_msg_response(msg, replier, req_msg, rsp_msg) {
   var rand = Math.floor(Math.random() * RAND_MAX);
   var rand_index = 0;


   for (var i=0; i < req_msg.length; i++) {
      if (msg.indexOf(req_msg[i]) != -1) {

         if (rsp_msg.length < 5) {
            replier.reply("대화기록이 부족해요..");
            return -1;
         }
         rand_index = rand % (rsp_msg.length - 3);

         java.lang.Thread.sleep(500);

         replier.reply("최근 대화를 요약해드릴게요!\n\n" + 
         rsp_msg[rand_index] + "\n" + 
         rsp_msg[rand_index + 1] + "\n" +
         rsp_msg[rand_index + 2]);

         return 0;
      }
   }
   
   return -1;
}


function help_response(msg, replier, req_msg) {
   for (var i=0; i < req_msg.length; i++) {
      if (msg.indexOf(req_msg[i]) != -1) {
         java.lang.Thread.sleep(500);
         replier.reply('/*\n * Tohru Bot\n * Version ' + BOT_VERSION + '\n */' +
            '\n\n 「"토르" + "명령어"」 형태로 동작합니다.\n\n' +
            '명령어 목록은 아래와 같습니다.\n   - 도움말, -h, --help\n   - 환영하기\n   - 뭐해\n   - 날씨\n   - 아침, 점심, 저녁추천\n   - 라면추천\n   - 치킨추천\n   - 애니추천\n   - 오늘의 애니\n   - 공부하기\n   - 비트코인\n   - 칼로리\n   - 로또번호\n   - 대화요약\n   - 퀴즈\n   - 가챠\n   - 강화\n\n' +
            '@github: git@github.com:wndk7720/Bot.git');
         return 0;
      }
   }
   
   return -1;
}


function nalssi_response(msg, replier, req_msg) {
   for (var i=0; i < req_msg.length; i++) {
      if (msg.indexOf(req_msg[i]) != -1) {
         replier.reply("밑에서 확인할 수 있어요!\nhttps://www.google.com/search?q=날씨");   
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
            replier.reply("현재 인기 키워드입니다!\n\n" + keywords.join("\n")); 
         } catch (e) { 
            replier.reply("못 알아 들었어요..ㅠ");
         } 
         
         return 0;
      }
   }
   
   return -1;
}


function meet_response(msg, replier, req_msg) {
   for (var i=0; i < req_msg.length; i++) {
      if (msg.indexOf(req_msg[i]) != -1) {
         replier.reply("반가워요~ 저는 토르라고 합니다!\n\n닉네임은 이름/나이/최애캐 설정해주시고, 간단한 자기소개(입문작/최애작/최애캐/가장 최근에 본 애니) 한번만 더 부탁드립니다~");
         return 0;
      }
   }
   
   return -1;
}


function yok_response(msg, replier, req_msg) {
   for (var i=0; i < req_msg.length; i++) {
      if (msg.indexOf(req_msg[i]) != -1) {
         replier.reply("'" + req_msg[i] + "' 이런 말 쓰면 안돼요!");
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
            
            replier.reply("오늘 방영하는 애니 목록입니다!\n\n" + keywords.join("\n")); 
         } catch (e) { 
            replier.reply("못 알아들었어요..ㅠㅠ"); 
         } 
         
         return 0;
      }
   }

   return -1
}


function recommend_ani_response(msg, replier, req_msg) {
   var page_rand = Math.floor(Math.random() * ANI_PAGE_RAND_MAX);
   var subject_rand = Math.floor(Math.random() * ANI_RECOMMEND_RAND_MAX);

   for (var i=0; i < req_msg.length; i++) {
      if (msg.indexOf(req_msg[i]) != -1) {
         try {
            var url = "https://anissia.net/api/anime/list/" + page_rand;
            var json = Utils.getWebText(url);
            json = json.replace(/(<([^>]+)>)/ig, "");

            var obj = JSON.parse(json);
            var content = obj.content;
            var data_len = content.length;
            subject_rand = subject_rand % data_len;

            var str = " - " + content[subject_rand].subject + " (" + content[subject_rand].genres + ")" + "\n   > 방영일 : " + content[subject_rand].startDate + "\n   > " + content[subject_rand].website;

            if (subject_rand < (data_len / 4)) {
               replier.reply("이거 재밌어요~\n\n" + str);  
            }
            else if (subject_rand < (data_len / 2)) {
               replier.reply("제가 요즘 보는 애니입니다!\n\n" + str);
            }
            else {
               replier.reply("이런 애니어떠신가요?\n\n" + str);  
            }
         } catch (e) {
            replier.reply("지금은 정신이 없어요ㅠㅠ");
         }

         return 0;
      }
   }

   return -1
}


function call_bot_command_response(msg, sender, isGroupChat, replier) {
   
   for (var i=0; i < bot_msg.length; i++) {
      if (msg.indexOf(bot_msg[i]) == 0) {
         if (help_response(msg, replier, help_msg) == 0) return 0;
         if (meet_response(msg, replier, meet_msg) == 0) return 0;
         if (cal_response(msg, replier, cal_msg) == 0) return 0;
         if (study_req_rsp(msg, isGroupChat, replier, study_msg) == 0) return 0;
         if (study_check_response(msg, replier, study_check_msg) == 0) return 0;
         if (study_del_response(msg, replier, study_del_msg) == 0) return 0;
         if (ani_quiz_response(msg, replier, ani_quiz_msg, ani_quiz_problem, ani_quiz_answer) == 0) return 0;
         if (invest_game_response(msg, replier, invest_msg) == 0) return 0;
         if (regards_response(msg, replier, regards_msg, regards_morning_reply, regards_daily_reply) == 0) return 0;
      }

      if (msg.indexOf(bot_msg[i]) != -1) {
         if (invest_game_purchase_response(msg, replier, invest_purchase_msg, sender) == 0) return 0;
         if (coin_response(msg, replier, coin_msg) == 0) return 0;
         if (nalssi_response(msg, replier, nalssi_msg) == 0) return 0;
         //if (silsigan_response(msg, replier, silsigan_msg) == 0) return 0;
         if (today_ani_response(msg, replier, today_ani_msg) == 0) return 0;
         if (recommend_ani_response(msg, replier, recommend_ani_msg) == 0) return 0;
         if (ramen_response(msg, replier, ramen_msg, ramen_content, ramen_sub_content) == 0) return 0;
         if (meal_response(msg, replier, chik_msg, chik_reply) == 0) return 0;
         if (basic_tohru_response(msg, replier, what_msg, what_reply, what_spe_reply, sender) == 0) return 0;
         if (hogam_up_response(msg, replier, hogam_up_msg, sender) == 0) return 0;
         if (hogam_down_response(msg, replier, hogam_down_msg, sender) == 0) return 0;
         if (meal_response(msg, replier, meal_msg, meal_reply) == 0) return 0;
         if (lotto_response(msg, replier, lotto_msg) == 0) return 0;
         if (gacha_response(msg, replier, gacha_msg) == 0) return 0;
         if (reinforce_response(msg, replier, reinforce_msg) == 0) return 0;
         if (sampling_msg_response(msg, replier, sampling_msg, sampling_data) == 0) return 0;

         if (basic_response(msg, replier, hello_msg, hello_reply) == 0) return 0;
         if (basic_response(msg, replier, morning_msg, morning_reply) == 0) return 0;
         if (basic_response(msg, replier, bye_msg, bye_reply) == 0) return 0;
         if (basic_response(msg, replier, dot_msg, dot_reply) == 0) return 0;
         if (basic_response(msg, replier, chool_msg, chool_reply) == 0) return 0;
         if (basic_response(msg, replier, toi_msg, toi_reply) == 0) return 0;
         if (basic_response(msg, replier, zzz_msg, zzz_reply) == 0) return 0;
         if (basic_response(msg, replier, atk_msg, atk_reply) == 0) return 0;
         if (basic_response(msg, replier, ga_msg, ga_reply) == 0) return 0;
         if (basic_response(msg, replier, thx_msg, thx_reply) == 0) return 0;
         if (basic_response(msg, replier, sorry_msg, sorry_reply) == 0) return 0;
         if (basic_response(msg, replier, doo_msg, doo_reply) == 0) return 0;
         if (basic_response(msg, replier, gojang_msg, gojang_reply) == 0) return 0;
         if (basic_response(msg, replier, sogae_msg, sogae_reply) == 0) return 0;
      }
   }
   
   return -1;
}


function response(room, msg, sender, isGroupChat, replier, ImageDB) {
   var today = new Date();
   var time_flag = 0;

   /* 우선 반응 명령어 */
   if (yok_response(msg, replier, yok_msg) == 0) return;

   if (basic_response(msg, replier, koba_msg, koba_reply) == 0) return;
   if (basic_response(msg, replier, kanna_msg, kanna_reply) == 0) return;
   if (basic_response(msg, replier, elma_msg, elma_reply) == 0) return;

   /* 호출 명령어 */
   if (call_bot_command_response(msg, sender, isGroupChat, replier) == 0) return;

   /* 샘플링용 메세지 저장 */
   sampling_data_store(msg, sender, isGroupChat);

   /* 기본적인 응답 */
   if (sake_basic_response(msg, replier, sake_msg, sake_reply) == 0) return;
   if (sometimes_basic_response(msg, replier, maid_msg, maid_reply) == 0) return;
   if (sometimes_basic_response(msg, replier, bomb_msg, bomb_reply) == 0) return;
   if (sometimes_basic_response(msg, replier, hello_msg, hello_reply) == 0) return;
   if (sometimes_basic_response(msg, replier, morning_msg, morning_reply) == 0) return;
   if (sometimes_basic_response(msg, replier, bye_msg, bye_reply) == 0) return;
   if (sometimes_basic_response(msg, replier, kkk_msg, kkk_reply) == 0) return;
   if (sometimes_basic_response(msg, replier, dot_msg, dot_reply) == 0) return;
   if (sometimes_basic_response(msg, replier, wow_msg, wow_reply) == 0) return;
   if (sometimes_basic_response(msg, replier, chool_msg, chool_reply) == 0) return;
   if (sometimes_basic_response(msg, replier, toi_msg, toi_reply) == 0) return;
   if (basic_tohru_response(msg, replier, bot_msg, bot_reply, bot_spe_reply, sender) == 0) return;

   /* 이벤트성 응답 */
   if (ani_quiz_answer_response(msg, replier, ani_quiz_answer, sender) == 0) return;
   if (study_response(msg, replier, study_req, study_rsp) == 0) return;
}
