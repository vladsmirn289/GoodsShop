package com.shop.GoodsShop.Model;

import com.shop.GoodsShop.Service.CategoryService;
import com.shop.GoodsShop.Service.ClientService;
import com.shop.GoodsShop.Service.ItemService;
import com.shop.GoodsShop.Service.OrderService;
import com.shop.GoodsShop.Utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InitDB {
    private CategoryService categoryService;
    private ItemService itemService;
    private ClientService clientService;
    private OrderService orderService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Autowired
    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public void init() {
        Category books = new Category("Книги");
        categoryService.save(books);

        /* --- Books Categories --- */

        Category fiction = new Category("Художественная литература", books);
        Category scienceBooks = new Category("Научная литература", books);
        Category programmingBooks = new Category("Программирование", books);

        categoryService.save(fiction);
        categoryService.save(scienceBooks);
        categoryService.save(programmingBooks);

        /* --- --- */

        Category stationery = new Category("Канцелярия");
        categoryService.save(stationery);

        /* --- Stationery Categories --- */

        Category notebooks = new Category("Блокноты, тетради, альбомы", stationery);
        Category writing = new Category("Письменные принадлежности", stationery);

        categoryService.save(notebooks);
        categoryService.save(writing);

        /* --- --- */

        Category clothes = new Category("Одежда");
        categoryService.save(clothes);

        /* --- Clothes Categories --- */

        Category babyClothes = new Category("Детская одежда", clothes);
        Category womenClothes = new Category("Женская одежда", clothes);
        Category menClothes = new Category("Мужская одежда", clothes);

        categoryService.save(babyClothes);
        categoryService.save(womenClothes);
        categoryService.save(menClothes);

        /* --- --- */

        Category electronics = new Category("Электроника");
        categoryService.save(electronics);

        /* --- Electronics Categories --- */

        Category computers = new Category("Компьютеры", electronics);
        Category peripherals = new Category("Периферия", electronics);

        categoryService.save(computers);
        categoryService.save(peripherals);

        /* --- --- */

        FileUtil fileUtil = new FileUtil();

        /* --- Book Items --- */

        /*String description = "Говард Филлипс Лавкрафт, не опубликовавший при жизни ни одной книги, " +
                "стал маяком и ориентиром жанра литературы ужасов, кумиром как широких читательских масс, " +
                "так и рафинированных интеллектуалов. Влияние его признавали такие мастера, как Борхес, " +
                "и такие кумиры миллионов, как Стивен Кинг, его рассказы неоднократно экранизировались, " +
                "а само имя писателя стало нарицательным. Франсуа Баранже — французский художник, дизайнер " +
                "компьютерных игр, концептуалист и футурист. Мечту оформить Лавкрафта он вынашивал много лет — " +
                "и вот наконец мечта сбылась: вашему вниманию предлагается классическая повесть «Зов Ктулху» с " +
                "иллюстрациями французского мастера. Наконец вы воочию увидите, что может быть, если Ктулху проснется…";
        String code = UUID.randomUUID().toString().substring(0, 8);
        Book callOfCthulhu = new Book("Зов Ктулху", 3000L, 0.57D, 800D, description, code, fiction,
                "Лавкрафт Г.Ф.", "Азбука", 64, "978-5-389-17639-3");
        callOfCthulhu.setBinding("Твёрдый");
        File image = new File("static/images/InitBooks/callOfCthulhu.jpg");
        callOfCthulhu.setImage(fileUtil.fileToBytes(image));

        description = "Жизнь Алисии Беренсон кажется идеальной. Известная художница вышла замуж за востребованного " +
                "модного фотографа. Она живет в одном из самых привлекательных и дорогих районов Лондона в роскошном " +
                "доме с большими окнами, выходящими в парк. Однажды поздним вечером, когда ее муж Габриэль возвращается " +
                "домой с очередной съемки, Алисия пять раз стреляет ему в лицо. И с тех пор не произносит ни слова.\n" +
                "Отказ Алисии говорить или давать какие-либо объяснения будоражит общественное воображение. Тайна делает " +
                "художницу знаменитой. И в то время как сама она находится на принудительном лечении, цена ее последней " +
                "работы – автопортрета с единственной надписью по-гречески \"АЛКЕСТА\" – стремительно растет.\n" +
                "Тео Фабер – криминальный психотерапевт. Он долго ждал возможности поработать с Алисией, заставить ее говорить. " +
                "Но что скрывается за его одержимостью безумной мужеубийцей, и к чему приведут все эти психологические эксперименты? " +
                "Возможно, к истине, которая угрожает поглотить и его самого...";
        code = UUID.randomUUID().toString().substring(0, 8);
        Book silentPatient = new Book("Безмолвный пациент", 1500L, 0.294D, 390D, description, code, fiction,
                "Михаэлидес Алекс", "Эксмо", 352, "978-5-04-105311-6");
        silentPatient.setBinding("Мягкий");
        image = new File("static/images/InitBooks/silentPatient.jpg");
        silentPatient.setImage(fileUtil.fileToBytes(image));

        description = "Книга знакомит читателя с творчеством известного английского писателя Артура Конан Дойла. " +
                "На страницах книги вы встретитесь со знакомыми персонажами и сможете проследить за раскрытием таинственных преступлений.";
        code = UUID.randomUUID().toString().substring(0, 8);
        Book sherlockHolmesTales = new Book("Рассказы о Шерлоке Холмсе", 1500L, 0.246D, 150D, description, code, fiction,
                "Конан Дойл Артур", "Самовар", 175, "978-5-9781-0119-5");
        sherlockHolmesTales.setBinding("Твёрдый");
        image = new File("static/images/InitBooks/sherlockHolmesTales.jpg");
        sherlockHolmesTales.setImage(fileUtil.fileToBytes(image));

        description = "Юрий Никулин, великий артист, замечательный клоун и чрезвычайно остроумный собеседник, " +
                "очень трепетно относился к читателям своих книжек. Он к ним обращался только на «вы» и просил принять " +
                "его самые лучшие пожелания. Он и анекдоты, которых знал тысячи, старался подбирать на любой вкус. Он был уверен, " +
                "что каждый найдет «свой анекдот» и станет смеяться, а то и просто сильно хохотать. Потом анекдот нужно запомнить и " +
                "рассказывать всем везде и всюду, но хорошо бы «к месту»: от этого анекдот только выиграет. А если кто-то, утверждал " +
                "Юрий Никулин, просто улыбнется, то и это будет очень хорошо и просто прекрасно. Итогом же прочтения этой книги " +
                "будет самый лучший и самый остроумный анекдот Юрия Никулина.";
        code = UUID.randomUUID().toString().substring(0, 8);
        Book jokesFromNikulin = new Book("Анекдоты от Никулина", 3000L, 0.449D, 500D, description, code, fiction,
                "Никулин Юрий Владимирович", "Зебра Е", 416, "978-5-907164-32-1");
        jokesFromNikulin.setBinding("Твёрдый");
        image = new File("static/images/InitBooks/jokesFromNikulin.jpg");
        jokesFromNikulin.setImage(fileUtil.fileToBytes(image));

        description = "Старший лейтенант Ибрагим Крушинин командует ротой спецназа на Северном Кавказе. " +
                "Он смел и беспощаден в бою. Ядовит – как шутят сослуживцы. Не случайно за старлеем закрепился " +
                "позывной Анчар. Во время очередной операции группа Крушинина попадает в засаду. Ибрагим подрывается " +
                "на мине и, раненный, оказывается в плену у бандитов. Неожиданно в главаре моджахедов он узнает своего " +
                "старшего брата, которого потерял в раннем детстве. Что делать – уничтожить бандитского эмира, " +
                "захватить его в плен или… Времени на размышления у Анчара не остается: на помощь своему командиру уже спешат бойцы спецназа…";
        code = UUID.randomUUID().toString().substring(0, 8);
        Book likeTwoDropsOfBlood = new Book("Как две капли крови", 2400L, 0.262D, 400D, description, code, fiction,
                "Самаров Сергей Васильевич", "Эксмо", 352, "978-5-04-109399-0");
        likeTwoDropsOfBlood.setBinding("Твёрдый");
        image = new File("static/images/InitBooks/likeTwoDropsOfBlood.jpg");
        likeTwoDropsOfBlood.setImage(fileUtil.fileToBytes(image));

        itemService.save(callOfCthulhu);
        itemService.save(silentPatient);
        itemService.save(sherlockHolmesTales);
        itemService.save(jokesFromNikulin);
        itemService.save(likeTwoDropsOfBlood);

        description = "Если раньше дифференциальные и интегральные исчисления были только уделом математиков, " +
                "сегодня эту тему уже проходят в старших классах школы. Однако те, кто в дальнейшем не связывает " +
                "свою жизнь с математикой, с трудом представляют, в какой сфере можно применить эти знания.\n" +
                "\n" +
                "В этой книге производные и интегралы рассматриваются не только в историческом, но и в " +
                "практическом контексте. Читатель узнает о том, какую роль они сыграли в наблюдении за звездами, " +
                "какова связь между функциями и выражением наклона, между интегрированием и делением земель. " +
                "Иллюстрации помогают представить математические задачи образно, а любопытные факты из жизни " +
                "ученых удачно дополняют изложение теории.\n" +
                "\n" +
                "Издание предназначено для старшеклассников, студентов вузов и всех любителей математики.";
        code = UUID.randomUUID().toString().substring(0, 8);
        Book derivativesAndIntegrals = new Book("Производные и интегралы", 5000L, 0.15D, 850D, description, code, scienceBooks,
                "Огами Т.", "ДМК Пресс", 130, "978-5-97060-814-2");
        derivativesAndIntegrals.setBinding("Мягкий");
        image = new File("static/images/InitBooks/derivativesAndIntegrals.jpg");
        derivativesAndIntegrals.setImage(fileUtil.fileToBytes(image));

        description = "Книга представляет собой учебник по функциональному анализу. " +
                "Этот учебник годится для первоначального изучения линейного функционального анализа, " +
                "но будет полезен и для углубленного изучения, поскольку содержит материал, который " +
                "обычно не включают в учебники по функциональному анализу. Несмотря на краткое изложение, " +
                "в учебнике все теоремы приведены с полными доказательствами. Многие понятия и утверждения " +
                "демонстрируются на модельных примерах. Книга будет полезна студентам и аспирантам, а " +
                "также всем желающим познакомиться с современной абстрактной математикой.\n";
        code = UUID.randomUUID().toString().substring(0, 8);
        Book functionalAnalysisFromZeroToUnits = new Book("Функциональный анализ от нуля до единиц", 4000L, 0.29D, 520D, description, code, scienceBooks,
                "Шамин Р.В.", "URSS", 272, "978-5-9710-7813-5");
        functionalAnalysisFromZeroToUnits.setBinding("Мягкий");
        image = new File("static/images/InitBooks/functionalAnalysisFromZeroToUnits.jpg");
        functionalAnalysisFromZeroToUnits.setImage(fileUtil.fileToBytes(image));

        description = "Практическое руководство по хирургии содержит современную и актуальную " +
                "информацию о диагностике, лечении и профилактике основных заболеваний и " +
                "синдромов, наиболее часто встречающихся в практике врача-хирурга амбулаторного звена.\n" +
                "Предназначено врачам-хирургам, врачам общей практики, клиническим ординаторам " +
                "и студентам старших курсов медицинских вузов.\n" +
                "Книга имеет электронную версию, активировав доступ к которой можно получить " +
                "дополнительные информационные материалы (уточняющие рекомендации, развернутые " +
                "речевые модули, нюансы взаимодействия лекарственных препаратов).\n";
        code = UUID.randomUUID().toString().substring(0, 8);
        Book surgeonTactics = new Book("Тактика врача-хирурга", 800L, 0.65D, 1780D, description, code, scienceBooks,
                "Шабунин А.В., Маер Р.Ю.", "ГЭОТАР-Медиа", 296, "978-5-9704-5523-4");
        surgeonTactics.setBinding("Мягкий");
        image = new File("static/images/InitBooks/surgeonTactics.jpg");
        surgeonTactics.setImage(fileUtil.fileToBytes(image));

        description = "Новые открытия в астрономии совершаются ежегодно, и новостные издания пестрят " +
                "сообщениями об очередных космических разработках. Но, хотя прошли времена, когда " +
                "астрономические явления порождали суеверия и страхи, научный подход ко Вселенной " +
                "пока еще не стал всеобщим уделом: ведь научно-популярной литературы о космосе, " +
                "рассчитанной на неспециалистов, очень мало.\n" +
                "Книга, которую вы держите в руках, призвана восполнить этот пробел. О происхождении " +
                "Земли и загадках Луны и Солнца, о планетах и галактиках, о Млечном Пути и новейших " +
                "данных из области космологии здесь рассказывается доступно и увлекательно. Для " +
                "наглядности текст сопровождается многочисленными иллюстрациями.\n" +
                "Издание предназначено для всех, кто интересуется астрономией, космологией и с" +
                "овременными научными изысканиями в этих областях.";
        code = UUID.randomUUID().toString().substring(0, 8);
        Book universeInQuestionsAndAnswers = new Book("Вселенная в вопросах и ответах", 800L, 0.14D, 852D, description, code, scienceBooks,
                "Ватанабэ Д.", "ДМК Пресс", 128, "978-5-97060-816-6");
        universeInQuestionsAndAnswers.setBinding("Мягкий");
        image = new File("static/images/InitBooks/universeInQuestionsAndAnswers.jpg");
        universeInQuestionsAndAnswers.setImage(fileUtil.fileToBytes(image));

        description = "\"Книга о пути жизни\" Лао-цзы, называемая по-китайски \"Дао-Дэ цзин\", занимает " +
                "после Библии второе место в мире по числу иностранных переводов. Происхождение этой " +
                "книги и личность ее автора окутаны множеством легенд, о которых известный переводчик " +
                "Владимир Малявин подробно рассказывает в своем предисловии. Само слово \"дао\" означает " +
                "путь, и притом одновременно путь мироздания, жизни и человеческого совершенствования. " +
                "А \"дэ\" – это внутренняя полнота жизни, незримо, но прочно связывающая все живое. " +
                "Секрет чтения Лао-цзы в том, чтобы постичь ту внутреннюю глубину смысла, которую внушает " +
                "мудрость, открывая в каждом суждении иной и противоположный смысл.\n";
        code = UUID.randomUUID().toString().substring(0, 8);
        Book bookAboutTheWayOfLife = new Book("Книга о пути жизни", 600L, 0.28D, 370D, description, code, scienceBooks,
                "Лао-цзы", "АСТ", 288, "978-5-17-122669-5");
        bookAboutTheWayOfLife.setBinding("Твёрдый");
        image = new File("static/images/InitBooks/bookAboutTheWayOfLife.jpg");
        bookAboutTheWayOfLife.setImage(fileUtil.fileToBytes(image));

        itemService.save(derivativesAndIntegrals);
        itemService.save(functionalAnalysisFromZeroToUnits);
        itemService.save(surgeonTactics);
        itemService.save(universeInQuestionsAndAnswers);
        itemService.save(bookAboutTheWayOfLife);

        description = "Учебно-практическое пособие охватывает первую, базовую, часть " +
                "учебного курса по языку SQL, созданного при участии российской компании " +
                "Postgres Professional. Учебный материал излагается в расчете на использование " +
                "системы управления базами данных PostgreSQL. Рассмотрено создание рабочей " +
                "среды, описан язык определения данных и основные операции выборки и изменения " +
                "данных. Показаны примеры использования транзакций, уделено внимание методам " +
                "оптимизации запросов. Материал сопровождается многочисленными практическими " +
                "примерами. Пособие может использоваться как для самостоятельного обучения, " +
                "так и проведения занятий под руководством преподавателя.";
        code = UUID.randomUUID().toString().substring(0, 8);
        Book postgreSQL = new Book("PostgreSQL. Основы языка SQL",900L, 0.35D, 690D, description, code, programmingBooks,
                "Моргунов Е.П.", "БХВ-Петербург", 336, "978-5-9775-4022-3");
        postgreSQL.setBinding("Мягкий");
        image = new File("static/images/InitBooks/postgreSQL.jpg");
        postgreSQL.setImage(fileUtil.fileToBytes(image));

        description = "Эта книга воплощает знания и опыт работы авторов с каркасом Spring " +
                "Framework и сопутствующими технологиями удаленного взаимодействия, " +
                "Hibernate, EJB и пр. Она дает возможность читателю не только усвоить " +
                "основные понятия и принципы работы с Spring Framework, но и научиться " +
                "рационально пользоваться этим каркасом для построения различных уровней и частей корпоративных приложений на языке Java, включая обработку транзакций, представление веб-содержимого и прочего содержимого, развертывание и многое другое. Полноценные примеры подобных приложений, представленные в этой книге, наглядно демонстрируют особенности совместного применения различных технологий и методик разработки приложений в Spring.\n" +
                "Пятое издание этой книги, давно уже пользующейся успехом у читателей, " +
                "обновлено по новой версии Spring Framework 5 и является самым " +
                "исчерпывающим и полным руководством по применению Spring среди всех " +
                "имеющихся. В нем представлен новый функциональный каркас веб-приложений, " +
                "микрослужбы, совместимость с версией Java 9 и прочие функциональные " +
                "возможности Spring. Прочитав эту обстоятельную книгу, вы сможете включить в " +
                "арсенал своих средств весь потенциал Spring для основательного построения " +
                "сложных приложений. Гибкий, легковесный каркас Spring Framework с открытым " +
                "кодом продолжает оставаться фактически ведущим в области разработки корпоративных " +
                "приложений на языке Java и самым востребованным среди разработчиков и " +
                "программирующих на Java. Он превосходно взаимодействует с другими гибкими, " +
                "легковесными технологиями Java с открытым кодом, включая Hibernate, Groovy, " +
                "MyBatis и прочие, а также с платформами Java ЕЕ и JPA 2.\n" +
                "Эта книга поможет вам:\n" +
                "• Выявить новые функциональные возможности в версии Spring Framework 5\n" +
                "• Научиться пользоваться Spring Framework вместе с Java 9\n" +
                "• Овладеть механизмом доступа к данным и обработки транзакций\n" +
                "• Освоить новый функциональный каркас веб-приложений\n" +
                "• Научиться создавать микрослужбы и другие веб-службы";
        code = UUID.randomUUID().toString().substring(0, 8);
        Book spring5ForProfessionals = new Book("Spring 5 для профессионалов", 300L, 1.592D, 4000D, description, code, programmingBooks,
                "Харроп Роб, Шефер Крис, Козмина Юлиана", "Диалектика / Вильямс", 1120, "978-5-907114-07-4, 978-1-4842-2807-4");
        spring5ForProfessionals.setBinding("Твёрдый");
        image = new File("static/images/InitBooks/spring5ForProfessionals.jpg");
        spring5ForProfessionals.setImage(fileUtil.fileToBytes(image));

        description = "Эта книга представляет собой обновленное руководство по использованию Git в " +
                "современных условиях. С тех пор как проект Git — распределенная система " +
                "управления версиями — был создан Линусом Торвальдсом, прошло много лет, и " +
                "система Git превратилась в доминирующую систему контроля версий, как для " +
                "коммерческих целей, так и для проектов с открытым исходным кодом. Эффективный и " +
                "хорошо реализованный контроль версий необходим для любого успешного веб-проекта. " +
                "Постепенно эту систему приняли на вооружение практически все сообщества разработчиков " +
                "ПО с открытым исходным кодом. Появление огромного числа графических интерфейсов для всех " +
                "платформ и поддержка IDE позволили внедрить Git в операционные системы семейства Windows. " +
                "Второе издание книги было обновлено для Git-версии 2.0 и уделяет большое внимание GitHub.";
        code = UUID.randomUUID().toString().substring(0, 8);
        Book proGit = new Book("Git для профессионального программиста", 700L, 0.51D, 1075D, description, code, programmingBooks,
                "Чакон Скотт, Штрауб Бен", "Питер", 496, "978-5-496-01763-3, 978-5-4461-1131-2");
        proGit.setBinding("Мягкий");
        image = new File("static/images/InitBooks/proGit.jpg");
        proGit.setImage(fileUtil.fileToBytes(image));

        description = "Более 10 лет первое издание этой книги считалось одним из " +
                "лучших практических руководств по программированию. Сейчас эта книга " +
                "полностью обновлена с учетом современных тенденций и технологий и " +
                "дополнена сотнями новых примеров, иллюстрирующих искусство и науку " +
                "программирования. Опираясь на академические исследования, с одной " +
                "стороны, и практический опыт коммерческих разработок ПО — с другой, " +
                "автор синтезировал из самых эффективных методик и наиболее эффективных " +
                "принципов ясное прагматичное руководство. Каков бы ни был ваш " +
                "профессиональный уровень, с какими бы средствами разработками вы ни " +
                "работали, какова бы ни была сложность вашего проекта, в этой книге " +
                "вы найдете нужную информацию, она заставит вас размышлять и поможет создать совершенный код.";
        code = UUID.randomUUID().toString().substring(0, 8);
        Book perfectCode = new Book("Совершенный код", 900L, 1.201D, 1275D, description, code, programmingBooks,
                "Макконнелл Стив", "Русская Редакция", 896, "978-5-7502-0064-1, 978-5-9909805-1-8");
        perfectCode.setBinding("Твёрдый");
        image = new File("static/images/InitBooks/perfectCode.jpg");
        perfectCode.setImage(fileUtil.fileToBytes(image));

        description = "Потоки являются фундаментальной частью платформы Java. " +
                "Многоядерные процессоры — это обыденная реальность, а эффективное " +
                "использование параллелизма стало необходимым для создания любого " +
                "высокопроизводительного приложения. Улучшенная виртуальная машина " +
                "Java, поддержка высокопроизводительных классов и богатый набор строительных " +
                "блоков для задач распараллеливания стали в свое время прорывом в разработке " +
                "параллельных приложений. В «Java Concurrency на практике» сами создатели " +
                "прорывной технологии объясняют не только принципы работы, но и рассказывают о паттернах проектирования.\n" +
                "Легко создать конкурентную программу, которая вроде бы будет работать. " +
                "Однако разработка, тестирование и отладка многопоточных программ " +
                "доставляют много проблем. Код перестает работать именно тогда, как это " +
                "важнее всего: при большой нагрузке. В «Java Concurrency на практике» вы " +
                "найдете как теорию, так и конкретные методы создания надежных, масштабируемых " +
                "и поддерживаемых параллельных приложений. Авторы не предлагают перечень API и " +
                "механизмов параллелизма, они знакомят с правилами проектирования, паттернами и " +
                "моделями, которые не зависят от версии Java и на протяжении многих лет остаются актуальными и эффективными.";
        code = UUID.randomUUID().toString().substring(0, 8);
        Book javaConcurrencyInPractice = new Book("Java Concurrency на практике", 1500L, 0.48D, 1430D, description, code, programmingBooks,
                "Гетц Б.", "Питер", 464, "978-5-4461-1314-9");
        javaConcurrencyInPractice.setBinding("Мягкий");
        image = new File("static/images/InitBooks/javaConcurrencyInPractice.jpg");
        javaConcurrencyInPractice.setImage(fileUtil.fileToBytes(image));

        itemService.save(postgreSQL);
        itemService.save(spring5ForProfessionals);
        itemService.save(proGit);
        itemService.save(perfectCode);
        itemService.save(javaConcurrencyInPractice);*/

        /* --- --- */
    }
}
