create database if not exists blog;

create table if not exists blog.users (
user_id varchar(50) not null primary key,
first_name varchar(25) not null,
last_name varchar(25) not null,
password varchar(100) not null,
email varchar(50) not null,
created_at datetime not null,
role varchar(50) not null,
enabled boolean
);

create table if not exists blog.articles(
article_id varchar(50) not null primary key,
title varchar(100),
articles.text text,
status varchar(50) not null,
user_id varchar(50) not null,
created_at datetime not null,
updated_at datetime,
constraint user_id foreign key(user_id) references blog.users(user_id)
);
    
create table if not exists blog.comments(
comment_id varchar(50) not null primary key,
message varchar(350) not null,
created_at datetime not null,
user_id varchar(50) not null,
constraint user_id_com foreign key(user_id) references blog.users(user_id),
article_id varchar(50) not null,
constraint article_id foreign key(article_id) references blog.articles(article_id)
);
   
create table if not exists blog.tags(
tag_id varchar(50) not null primary key,
name varchar(50)
);

create table if not exists blog.ARTICLES_TAGS(
ARTICLE_ID varchar(50) not null,
TAG_ID varchar(50));

insert into blog.users (user_id, first_name, last_name, password, email, created_at, role, enabled)
values ('2028e671778d1c4501778d1c56c90444', 'Ivan', 'Ivanov', '$2y$12$dA6ob6NoGimBOPMYAiNqiuwXOkSIhrdSryRyuB2pPmDz1sxG.XQ5m', 'ivan.ivanov@gmail.com',
'2021-06-05 00:00:00', 'ROLE_USER', true);
insert into blog.users (user_id, first_name, last_name, password, email, created_at,  role, enabled)
values ('2028e671778d1c4501778d1c56c90445', 'Petr', 'Petrov', '$2y$12$gdUrtVuK5q8lZt.p.ymL6.wTvvseZ9ngrvTrGBYAd.Gqxgs6UhycO', 'petr.petrov@gmail.com',
'2021-05-06 00:00:00','ROLE_USER', true);
insert into blog.users (user_id, first_name, last_name, password, email, created_at,  role, enabled)
values ('2028e671778d1c4501778d1c56c90446', 'Inna', 'Sidorova', '$2y$12$XqK1Lj9TcQCl.QVZEE.4se29hrCB65BJMEv/MMhZV.ZpEvPBudcxi', 'inna.sid@gmail.com',
'2021-06-06 00:00:00','ROLE_USER', true);
 
insert into blog.articles (article_id, title, text, status, user_id, created_at)
values ('4028e671778d1c4501778d1c56c90005', 'Summer in the village','The summer in the village is gorgeous,the crowing of roosters wakes people up as soon as the sun rises. The fragrant scents of flowering trees and flowers can not fail to attract attention, and a large piece of jasmine generally drives you crazy with its sweet smell.
I go to my grandmothers house in the village every summer vacation. There is fresh air, home-made products,a large farm and wide spaces for games. I have a friend Vaska, he lives two houses away from us, we always play together.
I also like to watch the little chickens and geese when Granny sends them out to graze on the back lawn.
They are so cute nibbling green grass, but it is worth their mothers to give a special sound and all at once hide under the wing of the parent.
When I was a kid, I used to pick the kulbaba blooms and blow up the white fluff, and now I help collect these greens, and we feed the rabbits. There is a lot of work in the village and I try my best to help because my grandmother is already very old, and she bakes my favorite flatbreads with cabbage as a reward for my help.',
'PUBLIC','2028e671778d1c4501778d1c56c90444', '2021-06-07 00:00:00' );

   
insert into blog.articles (article_id, title, text, status, user_id, created_at)
values ('4028e671778d1c4501778d1c56c90006', 'New technologies in our life', 'We live in the era of high technologies, and we use modern inventions in our everyday life because they have brought us much comfort. New technologies have spread on every field over the past 15 years. Moreover, they are rapidly changing. For example, video-recorders, DVD-players or compact disks have already become obsolete and have been replaced by more up-to-date devices. Today we can hardly imagine our life without such modern mobile devices as cell
phones or laptops. Our offices are fully equipped with computers, printers, scanners, air-conditioners, interactive whiteboards and wi-fi modems. Household appliances (vacuum-cleaners, coffee-machines,
dish-washers, food processors and others) help us to save our time and energy.
However, we should realize that digital and electronic inventions have both negative and positive impact on our daily life.
I am absolutely positive that new technologies or gadgets are making things faster, easier, more comfortable and interesting. For instance, if you install a GPS (Global Positioning System) in your car you’ll never get ost again. And could we imagine just 15 years ago all the things we can do on the wireless Internet nowadays: connecting with friends from all over the world, online shopping and banking, distance online learning, finding virtual relationships and even working from home? Isn’t that awesome?! Our parents used to go to post-offices to send letters or pay bills, they went to libraries to find a good book and they use telephone-booths for phone-calls.
On the other hand, I know some people who are strongly against some modern inventions because they really miss those days when they talked to each other face to face in reality, and not virtually. I partially agree
with that as I really believe that people are becoming anti-social and too dependent on their gadgets. Some of my friends also spend half of the time occupying their shiny gadgets (smart-phones or i-pads) even when we go out together. Besides, people who use various social networks a lot (such as Facebook or Instagram) should worry more about their privacy.
Summing up, I could say that there are serious arguments both for and against the use of new technologies but anyway it’s really difficult to imagine our life without them today.' ,
'PUBLIC', '2028e671778d1c4501778d1c56c90446', '2021-06-10 00:00:00' );

insert into blog.articles (article_id, title, text, status, user_id, created_at)
values ('4028e671778d1c4501778d1c56c90007', 'Benefits of travelling', 'Travelling to new and interesting places has always attracted people. It provides opportunities for adventure, fun and education. It benefits the society in a number of ways.
First of all, travelling enables people to take a break. People work hard most of the time and they need the time to relax and get away from their offices and homes to discover new places and cultures. Travelling helps people overcome burnout, depression and start working more efficiently than before.
Secondly, travel broadens the mind.  There is a quote by Saint Augustine: “The world is a book, and those who do not travel read only one page”. Travelling is a wonderful opportunity to observe and understand other cultures, experience new habits, national cuisine, festivities. And therefore, it helps prevent narrow-mindedness, ignorance, intolerance and racial prejudice.
Another benefit is that people improve their social skills while travelling because they meet a lot of people and make new friends. In other words, travelling increases confidence. Moreover, it`s a good way to practice foreign languages.
To sum it up, travelling has a lot of advantages. It is the best way to forget about your stressful work and daily routines and refresh your motivation. Life without travelling would be boring and monotonous.' ,
'PUBLIC', '2028e671778d1c4501778d1c56c90446', '2021-06-11 21:00:00' );

insert into blog.articles (article_id, title, text, status, user_id, created_at)
values ('4028e671778d1c4501778d1c56c90008', 'Grace Kelly', 'Grace Patricia Kelly was born in Philadelphia, Pennsylvania. She was the third of four children for John B. Kelly and Margaret Majer Kelly. Her father was a 3-time Olympic rowing champion, who was nearly elected mayor of Philadelphia. Her mother, a former model, was the first female coach of Womens team at the University of Pennsylvania. Grace did not inherit her parents athleticism. Instead, she developed a keen imagination.
Graces uncle George Kelly was a Pulitzer Prize-winning playwright. It was his name and guidance that helped land her a place at the American Academy of Dramatic Arts. Grace worked hard to refine her diction and years of ballet had given her natural poise. Her early success on the stage and on the television caught the attention of producer Stanley Kramer, who cast her opposite Gary Cooper in "High Noon". The film went on to receive 4 Academy Awards.
Graces film career was impressive. If incredibly short, she made just 11 films over 5 years. "The Country Girl", which co-starred Bing Crosby, earned her the Oscar for best actress in 1954.
Her most famous collaboration was Alfred Hitchcock, with whom she made three films: "Dial M for Murder", "Rear Window", and "To Catch a Thief". Grace became the epitome of the "Hitchcock Blonde". In Grace, the director found his "Snow Princess" - the ideal mixture of passion and restraint. By 1955 Grace had become a Hollywood icon: earning near-universal praise for her clothes, her discerning taste in film projects, and remarkable self-possession.
In May 1955, at a photoshoot in Cannes Grace Met Prince Rainier III of Monaco and the two began exchanging letters. The two were married in a Civil Service on April 18, 1956, and Grace became Her Serene Highness Princess Grace of Monaco. A religious ceremony was held the next day at Saint Nicholas Cathedral in front of 700 guests and a television audience of 30 million. Grace wore a gown gifted to her by MGM Studios and designed by Academy Award-winning costume designer Helen Rose.
The couple went on to have three children, Caroline, Albert (the current Prince of Monaco) and Stephanie. Despite many attempts to lure Grace back to Hollywood, her roles as mother and Princess of Monaco were her last.
On September 13, 1982 Grace and Stephanie were driving home from their country house when Grace suffered a minor stroke. She lost control of the car, which veered off the road and down the steep mountainside. Grace spent 24 hours in a coma before Rainier decided to take her off life support. Rainier never remarried and was buried alongside his wife in 2005.' ,
'DRAFT', '2028e671778d1c4501778d1c56c90445', '2021-06-12 10:20:00' );

insert into blog.articles (article_id, title, text, status, user_id, created_at)
values ('4028e671778d1c4501778d1c56c90009', 'Sport', 'People do sports for various reasons. Some do jogging in the morning to be in shape. Others go on hikes to get some fresh air or feel one on one with wildlife. Others train hard, trying to overtake rivals and experience the delightful joy of victory. Sports develop agility and physical strength. Competitions are held in accordance with clear rules, which provide all participants with equal chances to win. Many sports, for example, archery, jogging, wrestling, are rooted in antiquity when human life depended on accuracy, strength and speed. Others, such as basketball and volleyball, have been invented recently.
The popularity of sports in our time is largely promoted by television and the Internet. The major sporting events are watched by millions of people in different countries, and outstanding athletes become millionaires and gain worldwide fame. All athletes have to follow the rules strictly. The rules are followed by judges who can stop the game. In some sports, the referee uses a whistle for this. The referees can inform players about their decision with the flags or symbols.
Thanks to the sports form, players and spectators distinguish "their" from "strangers". Athletes under the uniform wear protective shields in football and hockey. They use different kinds of shoes: basketball sneakers, for example, are made with springy rubber soles, and football boots and running shoes are provided with studs. The equipment includes balls of a certain shape and size, bits, racquets. In team sports, players must constantly interact and try to win by common forces. The brightest "stars" in the team usually play in the attack and bring points their team. The defence plays no less important role, restraining the onslaught of rivals. Each player in his place contributes to the victory.
The rules of the game specify the size of the playing field, the size of the gate and the conventional notation - marking. These sizes can be different for adult players and children. For example, in basketball, the free throw distance can vary. The rules of games such as baseball and football set the maximum and minimum size of the field.
At the competitions, each participant tries to get ahead of rivals and show the best result - to put a record. In tennis, fencing, judo wrestling, athletes enter into a duel. In the race or at the race all the participants come out for the start, and in competitions in mountain skiing or archery, the athletes perform in turn. In some sports, such as gymnastics or diving, the result is judged by the number of points scored by the athlete.' ,
'DRAFT', '2028e671778d1c4501778d1c56c90444', '2021-06-13 11:20:00' );


insert into blog.comments (comment_id, message, created_at, user_id, article_id)
values ('7028e671778d1c4501778d1c56c90001', 'Good one!!!', '2021-06-08 00:00:00',
'2028e671778d1c4501778d1c56c90445', '4028e671778d1c4501778d1c56c90005');
insert into blog.comments (comment_id, message, created_at, user_id, article_id)
values ('7028e671778d1c4501778d1c56c90002', 'I love it', '2021-06-09 00:00:00',
'2028e671778d1c4501778d1c56c90445', '4028e671778d1c4501778d1c56c90006');
insert into blog.comments (comment_id, message, created_at, user_id, article_id)
values ('7028e671778d1c4501778d1c56c90003', 'Bla-bla-bla', '2021-06-11 00:00:00',
'2028e671778d1c4501778d1c56c90445', '4028e671778d1c4501778d1c56c90006');
   
insert into blog.tags (tag_id, name)
values ('1328e671778d1c4501778d1c56c90001' , 'Summer');
insert into blog.tags (tag_id, name)
values ('1328e671778d1c4501778d1c56c90002' , 'IT');
insert into blog.tags (tag_id, name)
values ('1328e671778d1c4501778d1c56c90003' , 'Lesson');
insert into blog.tags (tag_id, name)
values ('1328e671778d1c4501778d1c56c90004' , 'English');
insert into blog.tags (tag_id, name)
values ('1328e671778d1c4501778d1c56c90005' , 'Village');
insert into blog.tags (tag_id, name)
values ('1328e671778d1c4501778d1c56c90006' , 'Sport');
insert into blog.tags (tag_id, name)
values ('1328e671778d1c4501778d1c56c90007' , 'Travelling');
insert into blog.tags (tag_id, name)
values ('1328e671778d1c4501778d1c56c90008' , 'History');

insert into blog.articles_tags (article_id, tag_id)
values ( '4028e671778d1c4501778d1c56c90009', '1328e671778d1c4501778d1c56c90001' );
insert into blog.articles_tags (article_id, tag_id)
values ( '4028e671778d1c4501778d1c56c90008', '1328e671778d1c4501778d1c56c90002' );
insert into blog.articles_tags (article_id, tag_id)
values ( '4028e671778d1c4501778d1c56c90007', '1328e671778d1c4501778d1c56c90003' );
insert into blog.articles_tags (article_id, tag_id)
values ( '4028e671778d1c4501778d1c56c90006', '1328e671778d1c4501778d1c56c90004' );
insert into blog.articles_tags (article_id, tag_id)
values ( '4028e671778d1c4501778d1c56c90009', '1328e671778d1c4501778d1c56c90006' );
insert into blog.articles_tags (article_id, tag_id)
values ( '4028e671778d1c4501778d1c56c90008', '1328e671778d1c4501778d1c56c90001' );
