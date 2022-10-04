package com.dedoxyribose.library.repository.news

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dedoxyribose.library.model.News
import kotlinx.coroutines.delay
import org.joda.time.DateTime
import java.io.IOException
import javax.inject.Inject

class NewsRepository @Inject constructor() : INewsRepository {
    override suspend fun getNews(offset: Int): List<News> {
        delay(1200)
        return mockDataList.sortedByDescending { it.date }.drop(offset)
    }

    override fun createNewsDataSource(): PagingSource<Int, News> {
        return NewsSource(this)
    }

    private val mockDataList = listOf(
        News(
            id = 0,
            title = "С благодарностью старому году, с надеждой к новому...",
            subtitle = "Дорогие друзья! Уважаемые читатели!",
            text = "Новый год – это особый праздник! " +
                    "В этот день по нашей планете самым законным образом шагает сказка. " +
                    "Она приносит нам надежду, дарит множество подарков, " +
                    "заставляет надеяться на лучшее! С этой надеждой в душе, " +
                    "мы прощаемся с годом уходящим и встречаем грядущий 2020-й!!!",
            views = 34,
            date = DateTime.parse("2019-12-27T13:31:00")
        ),
        News(
            id = 1,
            title = "Цветоводство – занятие для тех, кто хочет быть счастливым",
            subtitle = null,
            text = "Красивые, яркие, торжественные и благоухающие цветы всегда привлекали " +
                    "человека. Они становятся участниками и радостных, и печальных событий," +
                    " помогают выразить настроение, чувства, вдохновляют и заставляют творить. " +
                    "Цветов в мире великое множество. Гордые гладиолусы, нежные орхидеи и простые" +
                    " ромашки. У каждого цветка свой неповторимый аромат, свои особые очертания, " +
                    "свой набор красок для хрупких лепестков. И у каждого из них – своя история" +
                    " и свои тайны. Тысячелетиями люди пытались найти объяснение этому настоящему " +
                    "живому чуду. Каждый любит цветы по-своему, кто-то просто созерцает, а кто-то" +
                    " выращивает их у себя на клумбах.",
            views = 28,
            date = DateTime.parse("2022-09-27T16:15:00")
        ),
        News(
            id = 2,
            title = "«Я люблю тебя, жизнь»!",
            subtitle = null,
            text = "Жизнь в небольшом городе подчинена сезонам. Вот закончился недавно у лысьвенцев сезон дачных отпусков, и свободного времени стало в разы больше! Хотя, наверняка, не только этим можно объяснить горячее желание многих наших читателей и друзей библиотеки посетить мероприятие со столь жизнеутверждающим названием «Я люблю тебя, жизнь»! в прошлую пятницу 23 сентября.\n" +
                    "\n" +
                    "В конференц-зале – очередная встреча талантов и поклонников –" +
                    " музыкальный коллектив «Созвучие» (руководитель – Ольга Валентиновна " +
                    "Андреева) открывал сезон после летнего перерыва. Фраза из легендарной" +
                    " песни стала лейтмотивом, задала тон всему действу. Задорные частушки " +
                    "для разогрева – от артистов, рассуждения о жизни, в которой всего вдоволь " +
                    "– «и гроз, и счастья», и о том, кто такие жизнелюбы – от ведущих, а также " +
                    "награждение лысьвенцев – финалистов городского фотоконкурса " +
                    "«Любимая. Модельная. Наша» перемежалось песнями и «интерактивом». " +
                    "Программа вечера была очень насыщенной: были и литературные викторины, " +
                    "и весёлые моменты, такие, как, например, игра «Сурдопереводчик». " +
                    "Украшением встречи стало дебютное выступление воспитанниц школы" +
                    " художественной гимнастики «GOLD STAR» (тренер С.В. Старкова). Девчонкам " +
                    "большое спасибо и дальнейших успехов в учёбе и в спорте!",
            views = 72,
            date = DateTime.parse("2022-09-27T14:26:00")
        ),
        News(
            id = 3,
            title = "Акция «Читай, Лысьва-2022»!",
            subtitle = "13-14 октября 2022 г. в Лысьвенском городском округе " +
                    "состоится акция «Читай, Лысьва-2022»!",
            text = "Организатор акции: Муниципальное бюджетное учреждение культуры " +
                    "«Лысьвенская библиотечная система»" +
                    "Цель: привлечение детей, подростков и молодежи к чтению книг о Лысьве, " +
                    "знакомству с местными авторами и новыми краеведческими изданиями." +
                    "К участию в акции «Читай, Лысьва-2022» приглашаются: все образовательные " +
                    "учреждения округа, в том числе учреждения дошкольного образования, " +
                    "средне-специального и высшего профессионального образования, общественные " +
                    "организации, молодежные объединения, библиотеки разных ведомств.",
            views = 65,
            date = DateTime.parse("2022-09-22T10:55:00")
        ),
        News(
            id = 3,
            title = "Уроки цветоводства",
            subtitle = "Осень – это самое загадочное время года. ",
            text = "Она подкрадывается тихо и незаметно. " +
                    "Осеннее дуновение ветра и лёгкая прохлада" +
                    "окутывает землю ещё с конца августа. " +
                    "А с первым сентябрьским днём всё становится " +
                    "по-настоящему таинственным. Кажется, что природа умирает, " +
                    "но нет, она просто готовится ко сну. Сентябрь – не повод" +
                    " вздохнуть с облегчением и грустью. Опытные садоводы знают," +
                    " что на участке еще остаются десятки неотложных дел, возникает " +
                    "потребность формировать или видоизменять свои цветники. " +
                    "Например, со второй половины августа можно приступать к делению и " +
                    "пересадке многолетников.\n" +
                    "\n" +
                    "А, вы уже знаете об этом? " +
                    "Тогда мы приглашаем Вас на нашу встречу «Цветочные брызги осени».\n" +
                    "\n" +
                    "25 сентября в конференц-зале Центральной библиотеки " +
                    "состоится встреча цветоводов – любителей, где можно " +
                    "будет поделиться своими секретами выращивания цветов, " +
                    "обменяться посадочным материалом и просто провести время в" +
                    " компании единомышленников – неугомонных людей, стремящихся " +
                    "к поиску нового и неизведанного в области цветоводства.",
            views = 41,
            date = DateTime.parse("2022-09-20T11:27:00")
        ),
    )

    class NewsSource(
        private val newsRepository: INewsRepository
    ) : PagingSource<Int, News>() {

        override fun getRefreshKey(state: PagingState<Int, News>): Int? {
            return state.anchorPosition
        }

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, News> {
            return try {
                val nextPage = params.key ?: 0
                val newsList = newsRepository.getNews(offset = params.loadSize * nextPage)
                LoadResult.Page(
                    data = newsList,
                    prevKey = if (nextPage == 0) null else nextPage - 1,
                    nextKey = if (newsList.isEmpty()) null else nextPage + 1
                )
            } catch (exception: IOException) {
                return LoadResult.Error(exception)
            }  /*catch (exception: HttpException) {
                return LoadResult.Error(exception)
            }*/
        }
    }
}