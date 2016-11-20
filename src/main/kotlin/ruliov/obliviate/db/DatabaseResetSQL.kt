package ruliov.obliviate.db

val RESET_DB_SQL = """
TRUNCATE words, translations RESTART IDENTITY;

INSERT INTO translations (id, text) VALUES
('1d729979-aab3-439e-afc8-3d1d2f165833', 'захват / сцепиться'),
('b3436608-8846-4245-a40e-a492787fa942', 'отказаться'),
('6c07bf86-b0ef-4eb4-9b38-4f9e4924c291', 'борьба (усилия)'),
('dfbe0d29-f37b-4ec6-81f5-fae2b5a087e2', 'плавить / предохранитель'),
('5ec3b243-a668-4266-8cbf-d276348da7e3', 'угрожающий / зловещий'),
('03fad02a-9350-4368-a579-825d28772747', 'бульканье'),
('6ae205f6-2018-4437-8d0f-cff6c62a8799', 'несчастный'),
('3fa48698-ba5f-4a0d-935f-66bed0893ff9', 'разражать / нервировать'),
('ab731fc5-a4b3-4039-878f-7bba6493b638', 'вмешиваться / мешать'),
('0d747419-685a-49ca-b0d3-c338a2c487c6', 'очевидный'),
('32f794ae-8fde-4452-a831-49911798cb70', 'выкачивать / спускать'),
('fa995ad8-01c1-471f-8d7c-8677fa4c9571', 'неохотный / вынужденный'),
('01d29d5e-8116-4720-ae21-2d2beed86fb9', 'опровергать'),
('ded49ce9-1df1-4c7d-995a-648ebaf61d49', 'голословное заявление'),
('f98dbe43-4cf7-4b7a-983a-0f9d3887c483', 'преследовать'),
('228d1e54-98aa-4622-add1-e250a035de9c', 'горевать'),
('d5eed300-1651-44e3-8043-b3724bc109ef', 'обвинять'),
('0d072623-7b97-4de1-894d-a96c98055401', 'упавший духом'),
('be34a42e-34d8-4be5-a9cb-dc4e26ffd14b', 'увядать / сохнуть'),
('a8c34a23-8e93-4899-96d1-8c38ae5f4d73', 'замаскированный'),
('15cd06d1-0bc1-48d6-aa29-2bfce15d6b01', 'отрыжка'),
('c9c4997e-f232-4517-899a-b978fabb9204', 'остаток'),
('ee8ccabf-bf8c-4733-99ee-326e60226cd0', 'стеллаж / рама'),
('c0da413e-ca95-4fe3-b1c6-6954a8844e20', 'яростный'),
('073c908c-a00d-43a8-bfab-c213978e350d', 'глубокий / проникновенный'),
('88b5f303-ab11-4d84-9eac-5de58f7abb84', 'единственный / подошва'),
('570bf245-9b97-43c9-b171-1160d0303c7e', 'обдумывать'),
('87d5482c-e742-467d-9fe5-af1b469bb845', 'скользить'),
('b003d51e-5daa-4487-866b-513eb3243d43', 'суматоха'),
('c4feea17-f4b4-46d9-98d1-9172518adafc', 'твёрдо'),
('a4887150-e882-4eca-b814-e7fd995dbf1b', 'обитать / жить'),
('a6389aeb-fc63-457f-bc9e-145081789faf', 'бедро'),
('f4eed111-ac38-4558-acc4-e96211513094', 'настаивать'),
('e04199ff-3c26-45c4-bad0-88f80a15b4b4', 'смягчаться'),
('d0e62554-ba33-47a9-b0e5-c5917d804f51', 'вонь'),
('161cb035-afd0-4acb-a3f2-b977a710160b', 'гранула'),
('4ba7bcb5-b4d4-4897-93fe-84eba6b71003', 'плутовской'),
('7474dc07-6b1e-4c61-a999-32424612695e', 'любящий'),
('5a35d206-463e-453f-aa02-80311548686c', 'ускользать'),
('d7d85ddc-7d09-48bb-b30b-196da1ce1f55', 'экспансивно'),
('f220f9fb-be64-4440-9089-63237eeb8106', 'переплетены'),
('6988a4ed-f2f2-4931-93d2-cdeada26a4ac', 'испуг'),
('f287eb0a-ce6a-419f-9a5f-4b9ed24af0d7', 'гнаться / преследовать'),
('1052ac87-c548-4663-a6c9-9ed07619e31a', 'вагон / экипаж'),
('6a3e96bb-c8a2-4e5d-8b05-a158ab1c2e6b', 'противоположность'),
('018d1aca-b336-4677-9c53-4cb77d4e8523', 'мятный'),
('ee7a6ce1-9b30-4225-9d92-45c3eb96c995', 'справедливый'),
('cbc8a946-54a6-4f4b-9349-bc6d77f9f015', 'мягкий / умеренный'),
('ac1661be-dd87-4970-bc56-e5f9a59f6b01', 'оскорбление'),
('644ded41-951f-4363-aaf3-95bb753022f7', 'отчаянный'),
('150fbc09-f733-4e2f-a424-73f3d24c962d', 'наследник'),
('f74e73ac-63a4-4ec0-8018-c193306cf3a5', 'свирепый / лютый'),
('e6a32b98-d6ad-4f4d-b50b-c5462f4acce2', 'довольно / вполне'),
('31a6e6f1-1873-4c45-8aa4-e41c9e427a50', 'поворот / кручение'),
('c8ae40b4-d65d-4793-8cd5-bd929d004d5c', 'немного / кусочек'),
('740514f4-1926-44cf-8b69-219c11501735', 'восторг / наслаждение'),
('9ef39993-c2b3-4199-a5da-92a0e200b1a0', 'смущать/сбивать с толку'),
('2dea6b1d-22e6-44d2-9655-b84940930415', 'забавлять / развлекать'),
('928813f6-7e7e-4489-83b1-a696d37abb1b', 'предложение / сделка'),
('fce6ed68-c36a-413c-a964-2fade609edfc', 'дрожать / трепетать'),
('6ab43cf1-be36-4d2c-97f4-b1db578e9085', 'забота / интерес'),
('47e3bcaf-e19b-4579-9a31-9789a1ab7a70', 'безжалостный / неумолимый'),
('8c354b40-7abe-413b-99af-8f83d8f056f2', 'притворство'),
('a7d00169-28c9-45b7-a242-ca5943b02a88', 'плавление / таяние');

INSERT INTO words (word, "translationId") VALUES
('grapple', '1d729979-aab3-439e-afc8-3d1d2f165833'),
('refuse', 'b3436608-8846-4245-a40e-a492787fa942'),
('struggle', '6c07bf86-b0ef-4eb4-9b38-4f9e4924c291'),
('fuse', 'dfbe0d29-f37b-4ec6-81f5-fae2b5a087e2'),
('ominous', '5ec3b243-a668-4266-8cbf-d276348da7e3'),
('gurgle', '03fad02a-9350-4368-a579-825d28772747'),
('miserable', '6ae205f6-2018-4437-8d0f-cff6c62a8799'),
('irritate', '3fa48698-ba5f-4a0d-935f-66bed0893ff9'),
('interfere', 'ab731fc5-a4b3-4039-878f-7bba6493b638'),
('obvious', '0d747419-685a-49ca-b0d3-c338a2c487c6'),
('deflate', '32f794ae-8fde-4452-a831-49911798cb70'),
('reluctant', 'fa995ad8-01c1-471f-8d7c-8677fa4c9571'),
('refute', '01d29d5e-8116-4720-ae21-2d2beed86fb9'),
('allegation', 'ded49ce9-1df1-4c7d-995a-648ebaf61d49'),
('obsess', 'f98dbe43-4cf7-4b7a-983a-0f9d3887c483'),
('grieve', '228d1e54-98aa-4622-add1-e250a035de9c'),
('accuse', 'd5eed300-1651-44e3-8043-b3724bc109ef'),
('droopy', '0d072623-7b97-4de1-894d-a96c98055401'),
('wither', 'be34a42e-34d8-4be5-a9cb-dc4e26ffd14b'),
('disguised', 'a8c34a23-8e93-4899-96d1-8c38ae5f4d73'),
('burp', '15cd06d1-0bc1-48d6-aa29-2bfce15d6b01'),
('residue', 'c9c4997e-f232-4517-899a-b978fabb9204'),
('rack', 'ee8ccabf-bf8c-4733-99ee-326e60226cd0'),
('furious', 'c0da413e-ca95-4fe3-b1c6-6954a8844e20'),
('profound', '073c908c-a00d-43a8-bfab-c213978e350d'),
('sole', '88b5f303-ab11-4d84-9eac-5de58f7abb84'),
('ponder', '570bf245-9b97-43c9-b171-1160d0303c7e'),
('slip', '87d5482c-e742-467d-9fe5-af1b469bb845'),
('kerfuffle', 'b003d51e-5daa-4487-866b-513eb3243d43'),
('firmly', 'c4feea17-f4b4-46d9-98d1-9172518adafc'),
('dwell', 'a4887150-e882-4eca-b814-e7fd995dbf1b'),
('hip', 'a6389aeb-fc63-457f-bc9e-145081789faf'),
('insist', 'f4eed111-ac38-4558-acc4-e96211513094'),
('relent', 'e04199ff-3c26-45c4-bad0-88f80a15b4b4'),
('stink', 'd0e62554-ba33-47a9-b0e5-c5917d804f51'),
('pellet', '161cb035-afd0-4acb-a3f2-b977a710160b'),
('puckish', '4ba7bcb5-b4d4-4897-93fe-84eba6b71003'),
('affectionate', '7474dc07-6b1e-4c61-a999-32424612695e'),
('elude', '5a35d206-463e-453f-aa02-80311548686c'),
('effusively', 'd7d85ddc-7d09-48bb-b30b-196da1ce1f55'),
('entwined', 'f220f9fb-be64-4440-9089-63237eeb8106'),
('fright', '6988a4ed-f2f2-4931-93d2-cdeada26a4ac'),
('chase', 'f287eb0a-ce6a-419f-9a5f-4b9ed24af0d7'),
('carriage', '1052ac87-c548-4663-a6c9-9ed07619e31a'),
('contrary', '6a3e96bb-c8a2-4e5d-8b05-a158ab1c2e6b'),
('peppermint', '018d1aca-b336-4677-9c53-4cb77d4e8523'),
('fair', 'ee7a6ce1-9b30-4225-9d92-45c3eb96c995'),
('mild', 'cbc8a946-54a6-4f4b-9349-bc6d77f9f015'),
('affront', 'ac1661be-dd87-4970-bc56-e5f9a59f6b01'),
('desperate', '644ded41-951f-4363-aaf3-95bb753022f7'),
('heir', '150fbc09-f733-4e2f-a424-73f3d24c962d'),
('fierce', 'f74e73ac-63a4-4ec0-8018-c193306cf3a5'),
('quite', 'e6a32b98-d6ad-4f4d-b50b-c5462f4acce2'),
('twist', '31a6e6f1-1873-4c45-8aa4-e41c9e427a50'),
('bit', 'c8ae40b4-d65d-4793-8cd5-bd929d004d5c'),
('delight', '740514f4-1926-44cf-8b69-219c11501735'),
('discombobulate', '9ef39993-c2b3-4199-a5da-92a0e200b1a0'),
('amuse', '2dea6b1d-22e6-44d2-9655-b84940930415'),
('proffer', '928813f6-7e7e-4489-83b1-a696d37abb1b'),
('dithering', 'fce6ed68-c36a-413c-a964-2fade609edfc'),
('concern', '6ab43cf1-be36-4d2c-97f4-b1db578e9085'),
('relentless', '47e3bcaf-e19b-4579-9a31-9789a1ab7a70'),
('pretense', '8c354b40-7abe-413b-99af-8f83d8f056f2'),
('melting', 'a7d00169-28c9-45b7-a242-ca5943b02a88');
"""