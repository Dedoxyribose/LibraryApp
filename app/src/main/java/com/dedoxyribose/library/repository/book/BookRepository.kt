package com.dedoxyribose.library.repository.book

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dedoxyribose.library.model.Book
import com.dedoxyribose.library.model.Genre
import kotlinx.coroutines.delay
import java.io.IOException
import javax.inject.Inject

class BookRepository @Inject constructor() : IBookRepository {
    override suspend fun getBooks(offset: Int, take: Int): List<Book> {
        delay(800)

        // симуляция ошибки
        /*if (Math.random() < 0.5) {
            throw IOException()
        }*/

        val from = offset
        val to = (offset + take).coerceAtMost(mockDataList.size)
        return if (from < to) {
            mockDataList.sortedByDescending { it.id }.subList(
                offset,
                to
            )
        } else {
            emptyList()
        }
    }

    override fun createBookDataSource(): PagingSource<Int, Book> {
        return BookSource(this)
    }

    private val mockDataList = listOf(
        Book(
            id = 0,
            title = "Королева Фей",
            author = "Эдмунд Спенсер",
            pageCount = 320,
            genres = listOf(Genre.EPIC),
            isPopular = false,
            coverUrl = "https://img3.labirint.ru/rc/37e232ef177b21afc8ce7e7309031001/363x561q80/books70/694991/cover.jpg?1564203802",
            description = "Эдмунд СПЕНСЕР (1552-1599) уже при жизни считался непревзойдённым мастером " +
                    "английского стиха, поэтом поэтов, привившим английскому стиху сладкозвучие и " +
                    "музыкальность.\n" +
                    "По замыслу автора поэтическая эпопея \"Королева фей\", сочиненная, \"чтобы " +
                    "склонить джентльмена или другого знатного человека к добродетели и достойному " +
                    "поведению\", должна была состоять из двенадцати книг, каждая из них посвящалась " +
                    "одной этической добродетели. Все эти достоинства воплощены в группе благородных " +
                    "героев, один из которых - король Артур.\n" +
                    "В настоящие издание вошел перевод первой книги поэмы \"Легенда о Рыцаре Алого" +
                    " Креста, или о Святости\" (1590), рассказывающей историю св. Георгия " +
                    "Победоносца, фантазией автора включенного в круг короля Артура. Кроме того" +
                    " приводятся выдержки из второй и третей книг - \"Легенды о сэре Гюйоне, или " +
                    "об Умеренности\" и \"Легенды о Бритомарте, или о Целомудрии\"\n" +
                    "Перевод выполнен поэтом, писателем, религиозным философом и мастером" +
                    " художественного перевода Владимиром МИКУШЕВИЧЕМ....\n"
        ),
        Book(
            id = 1,
            title = "Руслан и Людмила",
            author = "Александр Пушкин",
            pageCount = 192,
            genres = listOf(Genre.EPIC, Genre.DRAMA, Genre.FAIRY_TALE),
            isPopular = true,
            coverUrl = "https://img-gorod.ru/25/973/2597369_detail.jpg",
            description = "\"Руслан и Людмила\" - первая законченная и напечатанная поэма " +
                    "А. С. Пушкина. В обрамлении сказочного сюжета - волшебной истории о том," +
                    " как доблестный Руслан, отправившись на поиски похищенной невесты, " +
                    "побеждает коварство, обман и злое колдовство, - соединяются черты " +
                    "лирических жанров и героического эпоса, многоплановость повествования и" +
                    "авторская ирония, раскрывая великий талант автора.\n" +
                    "Многогранность, драматизм и поэтическую прелесть текста передают живые" +
                    " и яркие иллюстрации А. Д. Рейпольского.\n"
        ),
        Book(
            id = 2,
            title = "Косморама",
            author = "Ф.В.Одоевский",
            pageCount = 52,
            genres = listOf(Genre.DRAMA, Genre.FANTASTIC),
            isPopular = true,
            coverUrl = "https://a6.akniga.cc/uploads/y/ef54de3b20e78698/images2/c3b89d2a5d8042ba.jpg",
            description = "Загадочная, потрясающая глубиной идеи и размахом, повесть «Косморама» " +
                    "стала лучшим произведением Одоевского и, безусловно, входит в десятку лучших " +
                    "фантастических произведений русской литературы.\n" +
                    "\n" +
                    "Повесть была написана в 1837 г., и опубликована впервые в «Отечественных записках»"
        ),
        Book(
            id = 3,
            title = "Дверь в Англию",
            author = "Юлия Мазурова",
            pageCount = 112,
            genres = listOf(Genre.FANTASTIC, Genre.FANTASY, Genre.ADVENTURES, Genre.FAIRY_TALE),
            isPopular = true,
            coverUrl = "https://img3.labirint.ru/rc/ff3c9b34fd1f79102f36eefbb63c0a52/363x561q80/books84/838271/cover.png?1659439570",
            description = "Двенадцатилетняя Ася Самойлова никак не ожидала, что прямо из своей " +
                    "комнаты может перенестись в прошлое. И не куда-нибудь, а в Лондон времён " +
                    "царствования королевы Виктории. На календаре 1876 год, за окном дымящие трубы, " +
                    "двухэтажные омнибусы и люди в необычной одежде... Хорошо, что рядом с Асей " +
                    "оказывается Виктория Клиффорд, которая помогает новой подруге." +
                    " Однако случается так, что Ася остаётся совершенно одна в незнакомом городе. " +
                    "Полная тревоги прогулка по ночному Лондону запустит удивительную цепь событий," +
                    " которая неожиданным образом свяжет Асю с современными потомками Виктории.\n" +
                    "Для среднего школьного возраста.\n"
        ),
        Book(
            id = 4,
            title = "Миртл и тайна золотой лилии",
            author = "Элизабет Банс",
            pageCount = 448,
            genres = listOf(Genre.ADVENTURES, Genre.FAIRY_TALE, Genre.DETECTIVE),
            isPopular = true,
            coverUrl = "https://img4.labirint.ru/rc/45d3bf9ce119b9ebee12bb36b2646808/363x561q80/books88/871620/cover.png?1664367911",
            description = "Миртл Хардкасл всего двенадцать. Она не борется за звание" +
                    " Благовоспитанной Юной Леди, пренебрегает светскими мероприятиями" +
                    " и чтением газет для девочек, отдавая предпочтение учебнику по" +
                    " токсикологии и полицейским сводкам. И как может быть по-другому, " +
                    "если ее отец - городской прокурор?\n" +
                    "Миртл убеждена, что в доме, где живет пожилая мисс Вудхауз, сегодня " +
                    "ночью произошло ужасное преступление. Девочка начинает свое расследование," +
                    " и первая загадка на ее пути - пропажа лилий, выращиванию которых старушка " +
                    "отдавала все силы.\n" +
                    "Элизабет С. Банс - американская писательница, лауреат премии \"Эдгар\" в " +
                    "номинации \"Лучший детский роман\". Главная героиня ее детективной повести - " +
                    "девочка, ловко раскрывающая дела, с которыми не всегда справляется полиция, " +
                    "в тихом английском городке Суинберн. Проиллюстрировала книгу талантливая " +
                    "художница Юлия Добровольская.\n" +
                    "Для среднего и старшего школьного возраста.\n"
        )
    )

    class BookSource(
        private val bookRepository: IBookRepository
    ) : PagingSource<Int, Book>() {

        override fun getRefreshKey(state: PagingState<Int, Book>): Int? {
            return state.anchorPosition
        }

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Book> {
            return try {
                val nextPage = params.key ?: 0
                val bookList = bookRepository.getBooks(
                    offset = params.loadSize * nextPage,
                    take = params.loadSize
                )
                LoadResult.Page(
                    data = bookList,
                    prevKey = if (nextPage == 0) null else nextPage - 1,
                    nextKey = if (bookList.isEmpty()) null else nextPage + 1
                )
            } catch (exception: IOException) {
                return LoadResult.Error(exception)
            }
        }
    }

    override suspend fun getBook(id: Long): Book {
        delay(1200)
        return mockDataList.first { it.id == id }
    }
}
