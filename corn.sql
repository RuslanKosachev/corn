--
-- PostgreSQL database dump
--

-- Dumped from database version 12.7
-- Dumped by pg_dump version 12.7

-- Started on 2022-03-28 04:26:00

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 8 (class 2615 OID 262364)
-- Name: public; Type: SCHEMA; Schema: -; Owner: -
--

-- CREATE SCHEMA public;


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 205 (class 1259 OID 262388)
-- Name: city_connection; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.city_connection (
    id bigint NOT NULL,
    city_id_1 bigint NOT NULL,
    city_id_2 bigint NOT NULL,
    nominal_distance real NOT NULL,
    alternative_distance real,
    federal_expressway real,
    federal_road_regular real,
    regional_expressway real,
    regional_road_regular real,
    local_road real,
    obstacles smallint,
    coefficient_road_infrastructure double precision,
    coefficient_detour double precision,
    coefficient_obstacles double precision,
    coefficient_exceeding_distance double precision,
    coefficient_accessibility double precision,
    CONSTRAINT recurrence_city_ids CHECK ((city_id_1 <> city_id_2))
);


--
-- TOC entry 2876 (class 0 OID 0)
-- Dependencies: 205
-- Name: TABLE city_connection; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON TABLE public.city_connection IS 'Относительные данные пар городов';


--
-- TOC entry 2877 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN city_connection.id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.city_connection.id IS 'ID';


--
-- TOC entry 2878 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN city_connection.nominal_distance; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.city_connection.nominal_distance IS 'Расстояние между парой городов напрямую, км';


--
-- TOC entry 2879 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN city_connection.alternative_distance; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.city_connection.alternative_distance IS 'Расстояние между парой городов в объезд, км';


--
-- TOC entry 2880 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN city_connection.federal_expressway; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.city_connection.federal_expressway IS 'Автомобильная дорога федерального значения, имеющая класс «Автомагистраль» или «Скоростная дорога», км';


--
-- TOC entry 2881 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN city_connection.federal_road_regular; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.city_connection.federal_road_regular IS 'Автомобильная дорога федерального значения, имеющая класс «Дорога обычного типа (не скоростная дорога)», км';


--
-- TOC entry 2882 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN city_connection.regional_expressway; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.city_connection.regional_expressway IS 'Автомобильная дорога регионального или межмуниципального значения, имеющая класс «Автомагистраль» или «Скоростная дорога», км';


--
-- TOC entry 2883 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN city_connection.regional_road_regular; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.city_connection.regional_road_regular IS 'Автомобильная дорога регионального или межмуниципального значения, имеющая класс «Дорога обычного типа (не скоростная дорога)», км';


--
-- TOC entry 2884 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN city_connection.local_road; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.city_connection.local_road IS 'Автомобильная дорога местного значения, км';


--
-- TOC entry 2885 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN city_connection.obstacles; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.city_connection.obstacles IS 'Наличие преград на дороге (ж/д переезд, паромная переправа и т.д)';


--
-- TOC entry 2886 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN city_connection.coefficient_road_infrastructure; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.city_connection.coefficient_road_infrastructure IS 'kди – коэффициент дорожной инфраструктуры, определяемый как процентное соотношение пути по определенному виду дорожного покрытия от всего пути между рассматриваемыми объектами, умноженное на величину критерия для этого покрытия:

•	для автомобильной дороги федерального значения, имеющей класс «Автомагистраль» или «Скоростная дорога» kди = 1;

•	для автомобильной дороги федерального значения, имеющей класс «Дорога обычного типа (не скоростная дорога)» kди = 0,9;

•	для автомобильной дороги регионального или межмуниципального значения, имеющей класс «Автомагистраль» или «Скоростная дорога» kди = 0,75;

•	для автомобильной дороги регионального или межмуниципального значения, имеющая класс «Дорога обычного типа (не скоростная дорога)» kди = 0,7;

•	для автомобильной дороги местного значения kди = 0,5;';


--
-- TOC entry 2887 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN city_connection.coefficient_detour; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.city_connection.coefficient_detour IS 'kно – коэффициент наличия объезда. Он определяется как процентное отношение величины объезда к величине прямого пути, вычитаемое из значения 0,1. То есть чем меньше расстояние объездного пути, тем выше этот коэффициент, но не более 0,1. Для объезда, расстояние которого превышает прямой путь более чем в 2 раза критерий не учитывается';


--
-- TOC entry 2888 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN city_connection.coefficient_obstacles; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.city_connection.coefficient_obstacles IS 'kп – коэффициент наличия преград на пути следования. Он определяется как кол-во преград на пути следования, умноженное на значение в 0,05. Если преград нет – коэффициент не учитывается';


--
-- TOC entry 2889 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN city_connection.coefficient_exceeding_distance; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.city_connection.coefficient_exceeding_distance IS 'kпр – коэффициент превышения расстояния между рассматриваемыми объектами свыше 200 км. Каждый превышающий километр добавляет 0,001 этому коэффициенту';


--
-- TOC entry 2890 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN city_connection.coefficient_accessibility; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.city_connection.coefficient_accessibility IS 'kд - коэффициент доступности обслуживаемой техники';


--
-- TOC entry 228 (class 1255 OID 262473)
-- Name: calc_coefficients_road_infrastructure(public.city_connection); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION public.calc_coefficients_road_infrastructure(r public.city_connection) RETURNS record
    LANGUAGE plpgsql
    AS $$

begin

	

-- kди – коэффициент дорожной инфраструктуры

r.coefficient_road_infrastructure := 

	(r.federal_expressway * 1 

	+ r.federal_road_regular * 0.9 

	+ r.regional_expressway * 0.75 

	+ r.regional_road_regular * 0.7 

	+ r.local_road * 0.5)

	/ r.nominal_distance;



-- kно – коэффициент наличия объезда.

r.coefficient_detour := ((r.alternative_distance - r.nominal_distance) / r.nominal_distance) * 0.1;

IF 0 < r.coefficient_detour 

	AND r.coefficient_detour < 0.1 

THEN

    r.coefficient_detour := 0.1 - r.coefficient_detour;

else

	r.coefficient_detour := 0;

END IF;



-- kп – коэффициент наличия преград на пути следования

r.coefficient_obstacles := r.obstacles * 0.05;



-- kпр – коэффициент превышения расстояния между рассматриваемыми объектами свыше 200 км

r.coefficient_exceeding_distance := (r.nominal_distance - 200) * 0.001;

IF r.coefficient_exceeding_distance < 0 THEN

    r.coefficient_exceeding_distance := 0;

END IF;



-- kд - коэффициент доступности обслуживаемой техники

r.coefficient_accessibility :=

	r.coefficient_road_infrastructure 

	+ r.coefficient_detour

	- r.coefficient_obstacles 

	- r.coefficient_exceeding_distance;



--RAISE NOTICE 'calc_coefficients c_ri:% c_d:% c_o:% c_ed:% c_acces:%', c_ri, c_d, c_o, c_ed,  c_acces;



RETURN r;



END

$$;


--
-- TOC entry 231 (class 1255 OID 262509)
-- Name: calc_sum_of_dealerships(integer); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION public.calc_sum_of_dealerships(sum_machine integer) RETURNS smallint
    LANGUAGE plpgsql
    AS $$

begin



IF (sum_machine is not NULL) then

    RETURN ceiling(1 + ((sum_machine - 350) / 200));

END IF;

       

RETURN 0;



end;

$$;


--
-- TOC entry 213 (class 1255 OID 262444)
-- Name: text_index(bigint, bigint); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION public.text_index(city_id_1 bigint, city_id_2 bigint) RETURNS text
    LANGUAGE plpgsql IMMUTABLE
    AS $$

	begin

		RETURN concat(LEAST(city_id_1, city_id_2), '_',  GREATEST(city_id_1, city_id_2));

	       -- RETURN i + 1;

	END;

$$;


--
-- TOC entry 227 (class 1255 OID 262474)
-- Name: trigger_calc_coefficients_road_infrastructure(); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION public.trigger_calc_coefficients_road_infrastructure() RETURNS trigger
    LANGUAGE plpgsql
    AS $$

begin

-- RAISE NOTICE 'trigger_calc r:%  new.id:%', r.id,  new.id;

RETURN public.calc_coefficients_road_infrastructure(new);



end;

$$;


--
-- TOC entry 230 (class 1255 OID 262510)
-- Name: trigger_calc_sum_of_dealerships(); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION public.trigger_calc_sum_of_dealerships() RETURNS trigger
    LANGUAGE plpgsql
    AS $$

begin



        IF (new.machine is not NULL) then

            new.dealerships := public.calc_sum_of_dealerships(new.machine) ;

        END IF;

       

RETURN new;



end;

$$;


--
-- TOC entry 229 (class 1255 OID 262506)
-- Name: trigger_calc_sum_of_machine(); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION public.trigger_calc_sum_of_machine() RETURNS trigger
    LANGUAGE plpgsql
    AS $$

DECLARE

id_r integer := 0;

count_machine integer :=0;

begin



        IF (TG_OP = 'DELETE') then

            id_r := old.region_id;

        ELSIF (TG_OP = 'UPDATE') or (TG_OP = 'INSERT') THEN

             id_r := new.region_id;

        END IF;

       

       select sum(c.machine) from public.city c where c.region_id = id_r into count_machine;

      

update public.region

set machine = count_machine

where id = id_r;

       

RETURN NULL; -- возвращаемое значение для триггера AFTER игнорируется



end;

$$;


--
-- TOC entry 226 (class 1255 OID 262514)
-- Name: update_coefficient_road_infrastructure(bigint); Type: PROCEDURE; Schema: public; Owner: -
--

CREATE PROCEDURE public.update_coefficient_road_infrastructure(id_i bigint DEFAULT 0)
    LANGUAGE plpgsql
    AS $$



DECLARE

i public.city_connection;



begin



FOR i IN

    SELECT cc.* FROM public.city_connection cc

LOOP

    i := public.calc_coefficients_road_infrastructure(i);

       

	update public.city_connection 

	set coefficient_road_infrastructure = i.coefficient_road_infrastructure

	, coefficient_detour = i.coefficient_detour

	, coefficient_obstacles = i.coefficient_obstacles

	, coefficient_exceeding_distance = i.coefficient_exceeding_distance

	, coefficient_accessibility = i.coefficient_accessibility

	where id = i.id;

END LOOP;

	

end;

$$;


--
-- TOC entry 203 (class 1259 OID 262365)
-- Name: city; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.city (
    id bigint NOT NULL,
    full_name character varying(120) NOT NULL,
    population integer,
    machine integer,
    region_id bigint
);


--
-- TOC entry 2891 (class 0 OID 0)
-- Dependencies: 203
-- Name: TABLE city; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON TABLE public.city IS 'горо';


--
-- TOC entry 2892 (class 0 OID 0)
-- Dependencies: 203
-- Name: COLUMN city.id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.city.id IS 'ID';


--
-- TOC entry 2893 (class 0 OID 0)
-- Dependencies: 203
-- Name: COLUMN city.full_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.city.full_name IS 'Наименование районнго центра';


--
-- TOC entry 2894 (class 0 OID 0)
-- Dependencies: 203
-- Name: COLUMN city.population; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.city.population IS 'Население, чел';


--
-- TOC entry 2895 (class 0 OID 0)
-- Dependencies: 203
-- Name: COLUMN city.machine; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.city.machine IS 'Количество обслуживаемой техники, шт';


--
-- TOC entry 2896 (class 0 OID 0)
-- Dependencies: 203
-- Name: COLUMN city.region_id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.city.region_id IS 'отношение к региону';


--
-- TOC entry 206 (class 1259 OID 262391)
-- Name: city_connection_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.city_connection_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2897 (class 0 OID 0)
-- Dependencies: 206
-- Name: city_connection_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.city_connection_id_seq OWNED BY public.city_connection.id;


--
-- TOC entry 204 (class 1259 OID 262368)
-- Name: city_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.city_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2898 (class 0 OID 0)
-- Dependencies: 204
-- Name: city_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.city_id_seq OWNED BY public.city.id;


--
-- TOC entry 207 (class 1259 OID 262477)
-- Name: region; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.region (
    id bigint NOT NULL,
    full_name character varying NOT NULL,
    central_city_id bigint,
    machine integer,
    dealerships smallint
);


--
-- TOC entry 2899 (class 0 OID 0)
-- Dependencies: 207
-- Name: COLUMN region.id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.region.id IS 'Уникальный идентификатор';


--
-- TOC entry 2900 (class 0 OID 0)
-- Dependencies: 207
-- Name: COLUMN region.full_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.region.full_name IS 'Название области, края, региона';


--
-- TOC entry 2901 (class 0 OID 0)
-- Dependencies: 207
-- Name: COLUMN region.central_city_id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.region.central_city_id IS 'Региональный центр';


--
-- TOC entry 2902 (class 0 OID 0)
-- Dependencies: 207
-- Name: COLUMN region.machine; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.region.machine IS 'Количество обслуживаемой техники';


--
-- TOC entry 2903 (class 0 OID 0)
-- Dependencies: 207
-- Name: COLUMN region.dealerships; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.region.dealerships IS 'количество дилерских центров';


--
-- TOC entry 208 (class 1259 OID 262480)
-- Name: region_column1_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.region_column1_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2904 (class 0 OID 0)
-- Dependencies: 208
-- Name: region_column1_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.region_column1_seq OWNED BY public.region.id;


--
-- TOC entry 2719 (class 2604 OID 262370)
-- Name: city id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.city ALTER COLUMN id SET DEFAULT nextval('public.city_id_seq'::regclass);


--
-- TOC entry 2720 (class 2604 OID 262393)
-- Name: city_connection id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.city_connection ALTER COLUMN id SET DEFAULT nextval('public.city_connection_id_seq'::regclass);


--
-- TOC entry 2722 (class 2604 OID 262482)
-- Name: region id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.region ALTER COLUMN id SET DEFAULT nextval('public.region_column1_seq'::regclass);


--
-- TOC entry 2865 (class 0 OID 262365)
-- Dependencies: 203
-- Data for Name: city; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.city VALUES (3, 'с. Александров Гай', 13749, 0, 1);
INSERT INTO public.city VALUES (4, 'г. Аркадак', 11516, 19, 1);
INSERT INTO public.city VALUES (5, 'г. Аткарск', 24125, 11, 1);
INSERT INTO public.city VALUES (6, 'п. Базарный Карабулак', 9407, 3, 1);
INSERT INTO public.city VALUES (7, 'г. Балаково', 187523, 10, 1);
INSERT INTO public.city VALUES (8, 'г. Балашов', 75773, 17, 1);
INSERT INTO public.city VALUES (9, 'с. Балтай', 5184, 0, 1);
INSERT INTO public.city VALUES (10, 'г. Вольск', 61943, 4, 1);
INSERT INTO public.city VALUES (11, 'с. Воскресенское', 4489, 0, 1);
INSERT INTO public.city VALUES (12, 'п. Дергачи', 7704, 2, 1);
INSERT INTO public.city VALUES (13, 'п. Духовницкое', 4818, 9, 1);
INSERT INTO public.city VALUES (14, 'п. Екатериновка', 5753, 11, 1);
INSERT INTO public.city VALUES (15, 'г. Ершов', 18796, 8, 1);
INSERT INTO public.city VALUES (16, 'с. Ивантеевка', 5412, 1, 1);
INSERT INTO public.city VALUES (17, 'г. Калининск', 15508, 9, 1);
INSERT INTO public.city VALUES (18, 'г. Красноармейск', 22319, 1, 1);
INSERT INTO public.city VALUES (19, 'г. Красный Кут', 14130, 2, 1);
INSERT INTO public.city VALUES (20, 'п. Горный', 4565, 25, 1);
INSERT INTO public.city VALUES (21, 'п. Лысые Горы', 7074, 0, 1);
INSERT INTO public.city VALUES (22, 'г. Маркс', 30743, 11, 1);
INSERT INTO public.city VALUES (23, 'п. Новые Бурасы', 5796, 4, 1);
INSERT INTO public.city VALUES (24, 'г. Новоузенск', 15122, 0, 1);
INSERT INTO public.city VALUES (25, 'п. Озинки', 8106, 1, 1);
INSERT INTO public.city VALUES (26, 'с. Перелюб', 4934, 11, 1);
INSERT INTO public.city VALUES (27, 'г. Петровск', 28237, 2, 1);
INSERT INTO public.city VALUES (28, 'с. Питерка', 5127, 0, 1);
INSERT INTO public.city VALUES (38, 'г. Хвалынск', 12288, 0, 1);
INSERT INTO public.city VALUES (29, 'г. Пугачев', 40656, 22, 1);
INSERT INTO public.city VALUES (30, 'п. Ровное', 4305, 0, 1);
INSERT INTO public.city VALUES (31, 'п. Романовка', 6308, 11, 1);
INSERT INTO public.city VALUES (32, 'г. Ртищево', 38364, 4, 1);
INSERT INTO public.city VALUES (33, 'п. Самойловка', 6489, 17, 1);
INSERT INTO public.city VALUES (34, 'п. Степное', 11603, 6, 1);
INSERT INTO public.city VALUES (35, 'п. Татищево', 7431, 3, 1);
INSERT INTO public.city VALUES (36, 'п. Турки', 5486, 8, 1);
INSERT INTO public.city VALUES (2, 'г. Энгельс', 227049, 4, 1);
INSERT INTO public.city VALUES (1, 'г. Саратов', 838042, 1, 1);
INSERT INTO public.city VALUES (37, 'п. Мокроус', 6037, 0, 1);


--
-- TOC entry 2867 (class 0 OID 262388)
-- Dependencies: 205
-- Data for Name: city_connection; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.city_connection VALUES (314, 15, 16, 162, 0, 0, 0, 0, 67, 95, 2, 0.582716049382716, 0, 0.1, 0, 0.48271604938271606);
INSERT INTO public.city_connection VALUES (2, 1, 3, 263, 0, 0, 0, 0, 90, 173, 1, 0.5684410646387833, 0, 0.05, 0.063, 0.45544106463878326);
INSERT INTO public.city_connection VALUES (15, 1, 16, 271, 298, 0, 0, 0, 267.6, 4.3, 2, 0.6991513076303629, 0.09003690034151078, 0.1, 0.07100000000000001, 0.6181882079718737);
INSERT INTO public.city_connection VALUES (315, 15, 19, 131, 0, 0, 0, 0, 92, 39, 2, 0.6404580152671755, 0, 0.1, 0, 0.5404580152671755);
INSERT INTO public.city_connection VALUES (316, 15, 20, 66, 0, 0, 0, 0, 3, 63, 2, 0.5090909090909091, 0, 0.1, 0, 0.40909090909090917);
INSERT INTO public.city_connection VALUES (317, 15, 22, 174, 0, 0, 0, 0, 70, 104, 0, 0.5804597701149425, 0, 0, 0, 0.5804597701149425);
INSERT INTO public.city_connection VALUES (318, 15, 24, 220, 0, 0, 0, 0, 92, 128, 1, 0.5836363636363635, 0, 0.05, 0.02, 0.5136363636363634);
INSERT INTO public.city_connection VALUES (319, 15, 25, 115, 0, 0, 0, 0, 113, 2, 0, 0.6965217391304347, 0, 0, 0, 0.6965217391304347);
INSERT INTO public.city_connection VALUES (320, 15, 26, 210, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0.01, -0.01);
INSERT INTO public.city_connection VALUES (321, 15, 28, 176, 0, 0, 0, 0, 92, 84, 1, 0.6045454545454545, 0, 0.05, 0, 0.5545454545454545);
INSERT INTO public.city_connection VALUES (322, 15, 29, 121, 0, 0, 0, 0, 20, 101, 4, 0.5330578512396694, 0, 0.2, 0, 0.3330578512396694);
INSERT INTO public.city_connection VALUES (323, 15, 30, 202, 0, 0, 0, 0, 94, 108, 2, 0.593069306930693, 0, 0.1, 0.002, 0.49106930693069306);
INSERT INTO public.city_connection VALUES (324, 15, 34, 127, 0, 0, 0, 0, 112, 15, 0, 0.6763779527559054, 0, 0, 0, 0.6763779527559054);
INSERT INTO public.city_connection VALUES (325, 15, 37, 65, 0, 0, 0, 0, 61, 4, 1, 0.6876923076923076, 0, 0.05, 0, 0.6376923076923076);
INSERT INTO public.city_connection VALUES (326, 15, 2, 171, 0, 0, 0, 0, 164, 7, 0, 0.691812865497076, 0, 0, 0, 0.691812865497076);
INSERT INTO public.city_connection VALUES (111, 27, 23, 61, 0, 0, 0, 0, 56, 5, 1, 0.6836065573770491, 0, 0.05, 0, 0.6336065573770491);
INSERT INTO public.city_connection VALUES (327, 16, 19, 288, 0, 0, 0, 0, 159, 129, 4, 0.6104166666666667, 0, 0.2, 0.088, 0.3224166666666667);
INSERT INTO public.city_connection VALUES (328, 16, 20, 95, 0, 0, 0, 0, 64, 31, 1, 0.6347368421052632, 0, 0.05, 0, 0.5847368421052631);
INSERT INTO public.city_connection VALUES (329, 16, 22, 207, 0, 0, 0, 0, 192, 15, 1, 0.6855072463768115, 0, 0.05, 0.007, 0.6285072463768114);
INSERT INTO public.city_connection VALUES (330, 16, 24, 378, 0, 0, 0, 0, 159, 219, 2, 0.5841269841269842, 0, 0.1, 0.178, 0.3061269841269842);
INSERT INTO public.city_connection VALUES (331, 16, 25, 188, 0, 0, 0, 0, 39, 149, 0, 0.5414893617021277, 0, 0, 0, 0.5414893617021277);
INSERT INTO public.city_connection VALUES (332, 16, 26, 160, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0.05, 0, -0.05);
INSERT INTO public.city_connection VALUES (333, 16, 28, 334, 0, 0, 0, 0, 159, 175, 2, 0.5952095808383234, 0, 0.1, 0.134, 0.36120958083832344);
INSERT INTO public.city_connection VALUES (334, 16, 29, 40, 0, 0, 0, 0, 34, 6, 0, 0.6699999999999999, 0, 0, 0, 0.6699999999999999);
INSERT INTO public.city_connection VALUES (335, 16, 30, 368, 0, 0, 0, 0, 329, 21, 1, 0.6543478260869565, 0, 0.05, 0.168, 0.43634782608695644);
INSERT INTO public.city_connection VALUES (336, 16, 34, 272, 0, 0, 0, 0, 199, 73, 1, 0.6463235294117646, 0, 0.05, 0.07200000000000001, 0.5243235294117645);
INSERT INTO public.city_connection VALUES (337, 16, 37, 233, 0, 0, 0, 0, 128, 105, 3, 0.6098712446351932, 0, 0.15, 0.033, 0.4268712446351931);
INSERT INTO public.city_connection VALUES (338, 16, 2, 260, 0, 0, 0, 0, 245, 15, 0, 0.6884615384615385, 0, 0, 0.06, 0.6284615384615384);
INSERT INTO public.city_connection VALUES (339, 19, 20, 193, 0, 0, 0, 0, 95, 98, 3, 0.5984455958549223, 0, 0.15, 0, 0.4484455958549223);
INSERT INTO public.city_connection VALUES (340, 19, 22, 152, 0, 18, 0, 0, 94, 40, 2, 0.6828947368421052, 0, 0.1, 0, 0.5828947368421052);
INSERT INTO public.city_connection VALUES (341, 19, 24, 125, 0, 0, 0, 0, 0, 125, 1, 0.5, 0, 0.05, 0, 0.45);
INSERT INTO public.city_connection VALUES (342, 19, 25, 242, 0, 0, 0, 0, 205, 37, 2, 0.6694214876033058, 0, 0.1, 0.042, 0.5274214876033058);
INSERT INTO public.city_connection VALUES (343, 19, 26, 340, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0.05, 0.14, -0.19);
INSERT INTO public.city_connection VALUES (344, 19, 28, 80, 0, 0, 0, 0, 0, 80, 1, 0.5, 0, 0.05, 0, 0.45);
INSERT INTO public.city_connection VALUES (345, 19, 29, 248, 0, 0, 0, 0, 112, 136, 5, 0.5903225806451612, 0, 0.25, 0.048, 0.2923225806451612);
INSERT INTO public.city_connection VALUES (346, 19, 30, 72, 0, 0, 0, 0, 2, 70, 0, 0.5055555555555555, 0, 0, 0, 0.5055555555555555);
INSERT INTO public.city_connection VALUES (347, 19, 34, 70, 0, 0, 0, 0, 57, 13, 2, 0.6628571428571428, 0, 0.1, 0, 0.5628571428571428);
INSERT INTO public.city_connection VALUES (348, 19, 37, 70, 0, 0, 0, 0, 31, 39, 3, 0.5885714285714286, 0, 0.15, 0, 0.4385714285714286);
INSERT INTO public.city_connection VALUES (349, 19, 2, 114, 0, 0, 0, 0, 72, 42, 2, 0.6263157894736843, 0, 0.1, 0, 0.5263157894736843);
INSERT INTO public.city_connection VALUES (350, 20, 22, 162, 0, 0, 0, 0, 128, 34, 0, 0.6580246913580247, 0, 0, 0, 0.6580246913580247);
INSERT INTO public.city_connection VALUES (351, 20, 24, 283, 0, 0, 0, 0, 95, 188, 3, 0.5671378091872792, 0, 0.15, 0.083, 0.33413780918727914);
INSERT INTO public.city_connection VALUES (352, 20, 25, 172, 0, 0, 0, 0, 110, 62, 2, 0.627906976744186, 0, 0.1, 0, 0.5279069767441861);
INSERT INTO public.city_connection VALUES (353, 20, 26, 170, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0.15, 0, -0.15);
INSERT INTO public.city_connection VALUES (354, 20, 28, 238, 0, 0, 0, 0, 95, 143, 3, 0.5798319327731093, 0, 0.15, 0.038, 0.3918319327731093);
INSERT INTO public.city_connection VALUES (355, 20, 29, 55, 0, 0, 0, 0, 17, 38, 2, 0.5618181818181818, 0, 0.1, 0, 0.4618181818181818);
INSERT INTO public.city_connection VALUES (356, 20, 30, 265, 0, 0, 0, 0, 97, 168, 4, 0.5732075471698113, 0, 0.2, 0.065, 0.30820754716981125);
INSERT INTO public.city_connection VALUES (357, 20, 34, 190, 0, 0, 0, 0, 115, 75, 2, 0.6210526315789474, 0, 0.1, 0, 0.5210526315789474);
INSERT INTO public.city_connection VALUES (358, 20, 37, 128, 0, 0, 0, 0, 64, 64, 3, 0.6, 0, 0.15, 0, 0.44999999999999996);
INSERT INTO public.city_connection VALUES (359, 20, 2, 215, 0, 0, 0, 0, 181, 34, 0, 0.6683720930232557, 0, 0, 0.015, 0.6533720930232557);
INSERT INTO public.city_connection VALUES (360, 22, 24, 241, 0, 18, 0, 0, 94, 129, 1, 0.6153526970954357, 0, 0.05, 0.041, 0.5243526970954356);
INSERT INTO public.city_connection VALUES (361, 22, 25, 285, 0, 0, 0, 0, 183, 102, 0, 0.6284210526315789, 0, 0, 0.085, 0.5434210526315789);
INSERT INTO public.city_connection VALUES (362, 22, 26, 290, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0.15, 0.09, -0.24);
INSERT INTO public.city_connection VALUES (363, 22, 28, 197, 0, 18, 0, 0, 94, 85, 1, 0.6411167512690356, 0, 0.05, 0, 0.5911167512690355);
INSERT INTO public.city_connection VALUES (364, 22, 29, 167, 0, 0, 0, 0, 145, 22, 3, 0.6736526946107785, 0, 0.15, 0, 0.5236526946107785);
INSERT INTO public.city_connection VALUES (365, 22, 30, 160, 0, 18, 0, 0, 126, 16, 0, 0.7137499999999999, 0, 0, 0, 0.7137499999999999);
INSERT INTO public.city_connection VALUES (366, 22, 34, 71, 0, 0, 0, 0, 0, 71, 0, 0.5, 0, 0, 0, 0.5);
INSERT INTO public.city_connection VALUES (367, 22, 37, 148, 0, 18, 0, 0, 126, 4, 1, 0.731081081081081, 0, 0.05, 0, 0.681081081081081);
INSERT INTO public.city_connection VALUES (368, 22, 2, 52, 0, 0, 0, 0, 41, 11, 0, 0.6576923076923078, 0, 0, 0, 0.6576923076923078);
INSERT INTO public.city_connection VALUES (369, 24, 25, 331, 0, 0, 0, 0, 205, 126, 1, 0.6238670694864048, 0, 0.05, 0.131, 0.44286706948640475);
INSERT INTO public.city_connection VALUES (370, 24, 26, 330, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0.1, 0.13, -0.23);
INSERT INTO public.city_connection VALUES (371, 24, 28, 86, 0, 0, 0, 0, 0, 86, 0, 0.5, 0, 0, 0, 0.5);
INSERT INTO public.city_connection VALUES (372, 24, 29, 338, 0, 0, 0, 0, 112, 226, 6, 0.5662721893491124, 0, 0.3, 0.138, 0.1282721893491124);
INSERT INTO public.city_connection VALUES (373, 24, 30, 196, 0, 0, 0, 0, 2, 194, 1, 0.5020408163265306, 0, 0.05, 0, 0.45204081632653065);
INSERT INTO public.city_connection VALUES (374, 24, 34, 160, 0, 0, 0, 0, 20, 140, 1, 0.525, 0, 0.05, 0, 0.47500000000000003);
INSERT INTO public.city_connection VALUES (375, 24, 37, 160, 0, 0, 0, 0, 31, 129, 1, 0.5387500000000001, 0, 0.05, 0, 0.4887500000000001);
INSERT INTO public.city_connection VALUES (376, 24, 2, 204, 0, 0, 0, 0, 72, 132, 1, 0.5705882352941177, 0, 0.05, 0.004, 0.5165882352941177);
INSERT INTO public.city_connection VALUES (377, 25, 26, 100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO public.city_connection VALUES (378, 25, 28, 287, 0, 0, 0, 0, 205, 82, 1, 0.6428571428571429, 0, 0.05, 0.08700000000000001, 0.5058571428571429);
INSERT INTO public.city_connection VALUES (379, 25, 29, 148, 0, 0, 0, 0, 5, 143, 0, 0.5067567567567568, 0, 0, 0, 0.5067567567567568);
INSERT INTO public.city_connection VALUES (380, 25, 30, 313, 0, 0, 0, 0, 207, 106, 2, 0.6322683706070287, 0, 0.1, 0.113, 0.4192683706070287);
INSERT INTO public.city_connection VALUES (381, 25, 34, 239, 0, 0, 0, 0, 225, 14, 0, 0.6882845188284519, 0, 0, 0.039, 0.6492845188284518);
INSERT INTO public.city_connection VALUES (382, 25, 37, 176, 0, 0, 0, 0, 174, 2, 1, 0.6977272727272728, 0, 0.05, 0, 0.6477272727272727);
INSERT INTO public.city_connection VALUES (383, 25, 2, 282, 0, 0, 0, 0, 277, 5, 0, 0.6964539007092198, 0, 0, 0.082, 0.6144539007092198);
INSERT INTO public.city_connection VALUES (384, 26, 28, 390, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0.05, 0.19, -0.24);
INSERT INTO public.city_connection VALUES (385, 26, 29, 120, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0.05, 0, -0.05);
INSERT INTO public.city_connection VALUES (386, 26, 30, 430, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0.15, 0.23, -0.38);
INSERT INTO public.city_connection VALUES (387, 26, 34, 340, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0.14, -0.14);
INSERT INTO public.city_connection VALUES (101, 8, 35, 191, 0, 158, 5, 0, 6, 22, 0, 0.9303664921465968, 0, 0, 0, 0.9303664921465968);
INSERT INTO public.city_connection VALUES (388, 26, 37, 280, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0.05, 0.08, -0.13);
INSERT INTO public.city_connection VALUES (389, 26, 2, 340, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0.15, 0.14, -0.29000000000000004);
INSERT INTO public.city_connection VALUES (390, 28, 29, 293, 0, 0, 0, 0, 112, 181, 5, 0.5764505119453924, 0, 0.25, 0.093, 0.23345051194539243);
INSERT INTO public.city_connection VALUES (391, 28, 30, 152, 0, 0, 0, 0, 2, 150, 1, 0.5026315789473684, 0, 0.05, 0, 0.45263157894736844);
INSERT INTO public.city_connection VALUES (392, 28, 34, 116, 0, 0, 0, 0, 20, 96, 1, 0.5344827586206896, 0, 0.05, 0, 0.4844827586206896);
INSERT INTO public.city_connection VALUES (393, 28, 37, 116, 0, 0, 0, 0, 31, 85, 2, 0.553448275862069, 0, 0.1, 0, 0.45344827586206904);
INSERT INTO public.city_connection VALUES (394, 28, 2, 159, 0, 0, 0, 0, 72, 87, 1, 0.5905660377358491, 0, 0.05, 0, 0.5405660377358491);
INSERT INTO public.city_connection VALUES (120, 31, 11, 362, 0, 182, 98, 0, 6, 76, 2, 0.8629834254143646, 0, 0.1, 0.162, 0.6009834254143646);
INSERT INTO public.city_connection VALUES (121, 31, 14, 189, 0, 0, 48, 0, 91, 50, 2, 0.6978835978835979, 0, 0.1, 0, 0.5978835978835979);
INSERT INTO public.city_connection VALUES (122, 31, 17, 142, 0, 86, 0, 0, 6, 50, 1, 0.8112676056338028, 0, 0.05, 0, 0.7612676056338028);
INSERT INTO public.city_connection VALUES (123, 31, 18, 297, 0, 182, 56, 0, 6, 53, 1, 0.8858585858585859, 0, 0.05, 0.097, 0.7388585858585859);
INSERT INTO public.city_connection VALUES (124, 31, 21, 164, 0, 110, 0, 0, 6, 48, 1, 0.8426829268292683, 0, 0.05, 0, 0.7926829268292682);
INSERT INTO public.city_connection VALUES (125, 31, 23, 327, 0, 182, 47, 0, 6, 92, 2, 0.8394495412844036, 0, 0.1, 0.127, 0.6124495412844037);
INSERT INTO public.city_connection VALUES (126, 31, 32, 148, 0, 0, 0, 0, 99, 49, 2, 0.6337837837837837, 0, 0.1, 0, 0.5337837837837838);
INSERT INTO public.city_connection VALUES (127, 31, 33, 146, 0, 49, 0, 0, 49, 48, 1, 0.7349315068493151, 0, 0.05, 0, 0.684931506849315);
INSERT INTO public.city_connection VALUES (102, 8, 36, 85, 0, 0, 0, 0, 53, 32, 2, 0.6247058823529411, 0, 0.1, 0, 0.5247058823529411);
INSERT INTO public.city_connection VALUES (16, 1, 17, 118, 207, 101, 0, 0, 14, 2.2, 0, 0.9483050849478123, 0.024576270580291742, 0, 0, 0.9728813555281041);
INSERT INTO public.city_connection VALUES (103, 8, 38, 439, 0, 182, 238, 0, 6, 13, 2, 0.9268792710706151, 0, 0.1, 0.23900000000000002, 0.5878792710706151);
INSERT INTO public.city_connection VALUES (104, 27, 9, 138, 0, 0, 0, 0, 56, 82, 2, 0.581159420289855, 0, 0.1, 0, 0.48115942028985503);
INSERT INTO public.city_connection VALUES (105, 27, 10, 193, 0, 0, 46, 0, 56, 91, 2, 0.6533678756476684, 0, 0.1, 0, 0.5533678756476684);
INSERT INTO public.city_connection VALUES (106, 27, 11, 193, 0, 0, 159, 0, 1, 33, 3, 0.8305699481865284, 0, 0.15, 0, 0.6805699481865284);
INSERT INTO public.city_connection VALUES (107, 27, 14, 144, 0, 0, 89, 0, 1, 54, 2, 0.7486111111111112, 0, 0.1, 0, 0.6486111111111112);
INSERT INTO public.city_connection VALUES (108, 27, 17, 186, 0, 73, 74, 0, 1, 38, 1, 0.8564516129032259, 0, 0.05, 0, 0.8064516129032259);
INSERT INTO public.city_connection VALUES (109, 27, 18, 169, 0, 0, 158, 0, 1, 10, 2, 0.8751479289940829, 0, 0.1, 0, 0.7751479289940829);
INSERT INTO public.city_connection VALUES (110, 27, 21, 159, 0, 48, 69, 0, 1, 41, 1, 0.8257861635220126, 0, 0.05, 0, 0.7757861635220126);
INSERT INTO public.city_connection VALUES (165, 10, 21, 236, 0, 72, 157, 0, 0, 7, 2, 0.9186440677966102, 0, 0.1, 0.036000000000000004, 0.7826440677966102);
INSERT INTO public.city_connection VALUES (112, 27, 31, 323, 0, 158, 74, 0, 7, 84, 2, 0.8405572755417957, 0, 0.1, 0.123, 0.6175572755417957);
INSERT INTO public.city_connection VALUES (113, 27, 32, 174, 0, 0, 17, 0, 99, 58, 0, 0.6528735632183907, 0, 0, 0, 0.6528735632183907);
INSERT INTO public.city_connection VALUES (114, 27, 33, 262, 0, 109, 74, 0, 44, 35, 1, 0.8545801526717559, 0, 0.05, 0.062, 0.7425801526717559);
INSERT INTO public.city_connection VALUES (115, 27, 35, 90, 0, 0, 69, 0, 1, 20, 1, 0.808888888888889, 0, 0.05, 0, 0.758888888888889);
INSERT INTO public.city_connection VALUES (116, 27, 36, 256, 0, 0, 142, 0, 39, 75, 5, 0.75234375, 0, 0.25, 0.056, 0.44634375);
INSERT INTO public.city_connection VALUES (117, 27, 38, 274, 0, 0, 126, 0, 56, 92, 2, 0.7248175182481752, 0, 0.1, 0.074, 0.5508175182481753);
INSERT INTO public.city_connection VALUES (118, 31, 9, 389, 0, 182, 47, 0, 6, 154, 3, 0.7853470437017995, 0, 0.15, 0.189, 0.4463470437017995);
INSERT INTO public.city_connection VALUES (395, 29, 30, 328, 0, 0, 0, 0, 283, 27, 3, 0.6451219512195122, 0, 0.15, 0.128, 0.3671219512195122);
INSERT INTO public.city_connection VALUES (396, 29, 34, 232, 0, 0, 0, 0, 152, 80, 2, 0.6310344827586206, 0, 0.1, 0.032, 0.4990344827586206);
INSERT INTO public.city_connection VALUES (397, 29, 37, 183, 0, 0, 0, 0, 81, 102, 5, 0.5885245901639343, 0, 0.25, 0, 0.3385245901639343);
INSERT INTO public.city_connection VALUES (398, 29, 2, 220, 0, 0, 0, 0, 189, 22, 2, 0.6513636363636363, 0, 0.1, 0.02, 0.5313636363636363);
INSERT INTO public.city_connection VALUES (399, 30, 34, 142, 0, 0, 0, 0, 22, 120, 2, 0.5309859154929578, 0, 0.1, 0, 0.43098591549295784);
INSERT INTO public.city_connection VALUES (400, 30, 37, 142, 0, 0, 0, 0, 33, 109, 3, 0.5464788732394366, 0, 0.15, 0, 0.39647887323943654);
INSERT INTO public.city_connection VALUES (128, 31, 35, 233, 0, 158, 5, 0, 6, 64, 1, 0.8527896995708154, 0, 0.05, 0.033, 0.7697896995708153);
INSERT INTO public.city_connection VALUES (129, 31, 36, 127, 0, 0, 0, 0, 53, 74, 3, 0.5834645669291338, 0, 0.15, 0, 0.4334645669291338);
INSERT INTO public.city_connection VALUES (130, 31, 38, 481, 0, 182, 236, 0, 6, 57, 3, 0.8879417879417879, 0, 0.15, 0.281, 0.45694178794178786);
INSERT INTO public.city_connection VALUES (134, 9, 17, 252, 0, 97, 47, 0, 0, 108, 2, 0.7670634920634921, 0, 0.1, 0.052000000000000005, 0.6150634920634921);
INSERT INTO public.city_connection VALUES (136, 9, 21, 225, 0, 72, 47, 0, 0, 106, 2, 0.7435555555555556, 0, 0.1, 0.025, 0.6185555555555556);
INSERT INTO public.city_connection VALUES (140, 9, 33, 328, 0, 133, 47, 0, 43, 105, 2, 0.786280487804878, 0, 0.1, 0.128, 0.558280487804878);
INSERT INTO public.city_connection VALUES (141, 9, 35, 156, 0, 0, 49, 0, 0, 107, 2, 0.6256410256410256, 0, 0.1, 0, 0.5256410256410257);
INSERT INTO public.city_connection VALUES (142, 9, 36, 344, 0, 0, 108, 0, 38, 198, 5, 0.6476744186046512, 0, 0.25, 0.14400000000000002, 0.25367441860465123);
INSERT INTO public.city_connection VALUES (143, 9, 38, 208, 0, 0, 126, 0, 0, 82, 2, 0.7423076923076923, 0, 0.1, 0.008, 0.6343076923076924);
INSERT INTO public.city_connection VALUES (161, 10, 11, 95, 0, 0, 60, 0, 0, 35, 0, 0.7526315789473684, 0, 0, 0, 0.7526315789473684);
INSERT INTO public.city_connection VALUES (214, 17, 18, 160, 0, 97, 56, 0, 0, 7, 0, 0.943125, 0, 0, 0, 0.943125);
INSERT INTO public.city_connection VALUES (163, 10, 17, 263, 0, 97, 157, 0, 0, 9, 1, 0.92319391634981, 0, 0.05, 0.063, 0.81019391634981);
INSERT INTO public.city_connection VALUES (170, 10, 33, 339, 0, 133, 156, 0, 43, 7, 2, 0.9056047197640118, 0, 0.1, 0.139, 0.6666047197640118);
INSERT INTO public.city_connection VALUES (173, 10, 38, 90, 0, 0, 69, 0, 0, 31, 2, 0.8622222222222221, 0, 0.1, 0, 0.7622222222222221);
INSERT INTO public.city_connection VALUES (180, 11, 14, 232, 0, 0, 200, 0, 0, 32, 1, 0.8448275862068966, 0, 0.05, 0.032, 0.7628275862068965);
INSERT INTO public.city_connection VALUES (171, 10, 35, 167, 0, 0, 158, 0, 0, 9, 2, 0.8784431137724552, 0, 0.1, 0, 0.7784431137724552);
INSERT INTO public.city_connection VALUES (172, 10, 36, 382, 0, 0, 312, 0, 38, 32, 5, 0.8465968586387436, 0, 0.25, 0.182, 0.41459685863874357);
INSERT INTO public.city_connection VALUES (181, 11, 17, 224, 0, 97, 97, 0, 0, 30, 1, 0.8897321428571429, 0, 0.05, 0.024, 0.8157321428571428);
INSERT INTO public.city_connection VALUES (182, 11, 18, 187, 0, 0, 153, 0, 0, 34, 1, 0.8272727272727274, 0, 0.05, 0, 0.7772727272727273);
INSERT INTO public.city_connection VALUES (183, 11, 21, 197, 0, 72, 97, 0, 0, 28, 1, 0.8796954314720813, 0, 0.05, 0, 0.8296954314720812);
INSERT INTO public.city_connection VALUES (184, 11, 23, 122, 0, 0, 50, 0, 0, 72, 0, 0.6639344262295082, 0, 0, 0, 0.6639344262295082);
INSERT INTO public.city_connection VALUES (187, 11, 32, 288, 0, 0, 252, 0, 8, 28, 1, 0.8555555555555556, 0, 0.05, 0.088, 0.7175555555555556);
INSERT INTO public.city_connection VALUES (188, 11, 33, 301, 0, 133, 97, 0, 43, 28, 1, 0.8784053156146179, 0, 0.05, 0.101, 0.7274053156146179);
INSERT INTO public.city_connection VALUES (189, 11, 35, 129, 0, 0, 99, 0, 0, 30, 1, 0.8069767441860466, 0, 0.05, 0, 0.7569767441860465);
INSERT INTO public.city_connection VALUES (190, 11, 36, 343, 0, 0, 252, 0, 38, 53, 4, 0.8160349854227404, 0, 0.2, 0.14300000000000002, 0.4730349854227405);
INSERT INTO public.city_connection VALUES (191, 11, 38, 176, 0, 0, 141, 0, 0, 35, 1, 0.8204545454545454, 0, 0.05, 0, 0.7704545454545454);
INSERT INTO public.city_connection VALUES (202, 14, 17, 66, 0, 0, 0, 0, 64, 2, 0, 0.6939393939393939, 0, 0, 0, 0.6939393939393939);
INSERT INTO public.city_connection VALUES (203, 14, 18, 195, 0, 0, 187, 0, 0, 8, 0, 0.8835897435897436, 0, 0, 0, 0.8835897435897436);
INSERT INTO public.city_connection VALUES (204, 14, 21, 93, 0, 25, 0, 0, 64, 4, 0, 0.7720430107526881, 0, 0, 0, 0.7720430107526881);
INSERT INTO public.city_connection VALUES (81, 6, 23, 41, 0, 0, 0, 0, 0, 41, 0, 0.5, 0, 0, 0, 0.5);
INSERT INTO public.city_connection VALUES (205, 14, 23, 156, 0, 0, 58, 0, 0, 98, 1, 0.6487179487179487, 0, 0.05, 0, 0.5987179487179487);
INSERT INTO public.city_connection VALUES (209, 14, 32, 58, 0, 0, 48, 0, 8, 2, 0, 0.8586206896551725, 0, 0, 0, 0.8586206896551725);
INSERT INTO public.city_connection VALUES (210, 14, 33, 147, 0, 36, 0, 0, 107, 4, 0, 0.7680272108843537, 0, 0, 0, 0.7680272108843537);
INSERT INTO public.city_connection VALUES (211, 14, 35, 105, 0, 0, 102, 0, 0, 3, 0, 0.8885714285714286, 0, 0, 0, 0.8885714285714286);
INSERT INTO public.city_connection VALUES (17, 1, 18, 80, 0, 4.7, 56, 0, 14, 5.7, 1, 0.8468749964237213, 0, 0.05, 0, 0.7968749964237213);
INSERT INTO public.city_connection VALUES (212, 14, 36, 113, 0, 0, 48, 0, 38, 27, 3, 0.7371681415929203, 0, 0.15, 0, 0.5871681415929203);
INSERT INTO public.city_connection VALUES (213, 14, 38, 351, 0, 0, 341, 0, 0, 10, 2, 0.8886039886039887, 0, 0.1, 0.151, 0.6376039886039887);
INSERT INTO public.city_connection VALUES (133, 9, 14, 233, 0, 0, 58, 0, 0, 175, 2, 0.5995708154506437, 0, 0.1, 0.033, 0.4665708154506437);
INSERT INTO public.city_connection VALUES (162, 10, 14, 270, 0, 0, 261, 0, 0, 9, 2, 0.8866666666666667, 0, 0.1, 0.07, 0.7166666666666668);
INSERT INTO public.city_connection VALUES (215, 17, 21, 27, 0, 25, 0, 0, 0, 2, 0, 0.9629629629629629, 0, 0, 0, 0.9629629629629629);
INSERT INTO public.city_connection VALUES (216, 17, 23, 189, 0, 97, 47, 0, 0, 45, 1, 0.8560846560846561, 0, 0.05, 0, 0.8060846560846561);
INSERT INTO public.city_connection VALUES (217, 17, 32, 122, 0, 0, 50, 0, 72, 0, 0, 0.7819672131147541, 0, 0, 0, 0.7819672131147541);
INSERT INTO public.city_connection VALUES (218, 17, 33, 81, 0, 36, 0, 0, 43, 2, 0, 0.8283950617283949, 0, 0, 0, 0.8283950617283949);
INSERT INTO public.city_connection VALUES (219, 17, 35, 96, 0, 73, 5, 0, 0, 18, 0, 0.9010416666666666, 0, 0, 0, 0.9010416666666666);
INSERT INTO public.city_connection VALUES (164, 10, 18, 225, 0, 0, 212, 0, 0, 13, 2, 0.8768888888888889, 0, 0.1, 0.025, 0.7518888888888889);
INSERT INTO public.city_connection VALUES (220, 17, 36, 172, 0, 86, 59, 0, 0, 27, 2, 0.8872093023255814, 0, 0.1, 0, 0.7872093023255814);
INSERT INTO public.city_connection VALUES (221, 17, 38, 344, 0, 97, 237, 0, 0, 10, 2, 0.9165697674418605, 0, 0.1, 0.14400000000000002, 0.6725697674418605);
INSERT INTO public.city_connection VALUES (222, 18, 21, 133, 0, 72, 56, 0, 0, 5, 0, 0.9390977443609023, 0, 0, 0, 0.9390977443609023);
INSERT INTO public.city_connection VALUES (223, 18, 23, 152, 0, 0, 103, 0, 0, 49, 1, 0.7710526315789474, 0, 0.05, 0, 0.7210526315789474);
INSERT INTO public.city_connection VALUES (224, 18, 32, 252, 0, 0, 238, 0, 8, 6, 0, 0.8841269841269842, 0, 0, 0.052000000000000005, 0.8321269841269842);
INSERT INTO public.city_connection VALUES (401, 30, 2, 93, 0, 0, 0, 0, 79, 14, 0, 0.6698924731182795, 0, 0, 0, 0.6698924731182795);
INSERT INTO public.city_connection VALUES (225, 18, 33, 237, 0, 133, 56, 0, 43, 5, 0, 0.9113924050632911, 0, 0, 0.037, 0.8743924050632911);
INSERT INTO public.city_connection VALUES (226, 18, 35, 93, 0, 0, 85, 0, 0, 8, 0, 0.8655913978494624, 0, 0, 0, 0.8655913978494624);
INSERT INTO public.city_connection VALUES (227, 18, 36, 307, 0, 0, 238, 0, 38, 31, 3, 0.8348534201954397, 0, 0.15, 0.107, 0.5778534201954397);
INSERT INTO public.city_connection VALUES (228, 18, 38, 306, 0, 0, 293, 0, 0, 13, 2, 0.8830065359477124, 0, 0.1, 0.106, 0.6770065359477124);
INSERT INTO public.city_connection VALUES (135, 9, 18, 214, 0, 0, 103, 0, 0, 111, 2, 0.6925233644859813, 0, 0.1, 0.014, 0.5785233644859813);
INSERT INTO public.city_connection VALUES (229, 21, 23, 162, 0, 72, 47, 0, 0, 43, 1, 0.8382716049382717, 0, 0.05, 0, 0.7882716049382716);
INSERT INTO public.city_connection VALUES (230, 21, 32, 149, 0, 25, 50, 0, 72, 2, 0, 0.8147651006711409, 0, 0, 0, 0.8147651006711409);
INSERT INTO public.city_connection VALUES (231, 21, 33, 104, 0, 61, 0, 0, 43, 0, 0, 0.8759615384615385, 0, 0, 0, 0.8759615384615385);
INSERT INTO public.city_connection VALUES (232, 21, 35, 69, 0, 48, 5, 0, 0, 16, 0, 0.8768115942028986, 0, 0, 0, 0.8768115942028986);
INSERT INTO public.city_connection VALUES (233, 21, 36, 195, 0, 110, 0, 0, 60, 25, 2, 0.8435897435897436, 0, 0.1, 0, 0.7435897435897436);
INSERT INTO public.city_connection VALUES (234, 21, 38, 317, 0, 72, 238, 0, 0, 7, 2, 0.9138801261829654, 0, 0.1, 0.117, 0.6968801261829655);
INSERT INTO public.city_connection VALUES (235, 23, 32, 212, 0, 0, 108, 0, 8, 96, 1, 0.7113207547169812, 0, 0.05, 0.012, 0.6493207547169811);
INSERT INTO public.city_connection VALUES (132, 9, 11, 116, 0, 0, 14, 0, 0, 102, 1, 0.5482758620689655, 0, 0.05, 0, 0.4982758620689655);
INSERT INTO public.city_connection VALUES (137, 9, 23, 77, 0, 0, 0, 0, 0, 77, 1, 0.5, 0, 0.05, 0, 0.45);
INSERT INTO public.city_connection VALUES (166, 10, 23, 132, 0, 0, 46, 0, 0, 86, 1, 0.6393939393939394, 0, 0.05, 0, 0.5893939393939394);
INSERT INTO public.city_connection VALUES (169, 10, 32, 326, 0, 0, 311, 0, 8, 7, 2, 0.8865030674846628, 0, 0.1, 0.126, 0.6605030674846628);
INSERT INTO public.city_connection VALUES (1, 1, 2, 12, 40, 0, 0, 0, 12, 0, 0, 0.6999999999999998, 0, 0, 0, 0.6999999999999998);
INSERT INTO public.city_connection VALUES (6, 1, 7, 163, 178, 0, 0, 0, 163, 0, 2, 0.7, 0.09079754576086999, 0.1, 0, 0.6907975457608699);
INSERT INTO public.city_connection VALUES (7, 1, 8, 213, 290, 186, 6, 0, 21, 0, 0, 0.9676056338028168, 0.06384976506233216, 0, 0.013000000000000001, 1.018455398865149);
INSERT INTO public.city_connection VALUES (8, 1, 9, 135, 295, 0, 14, 0, 15.4, 105.8, 2, 0.5650370463618525, 0, 0.1, 0, 0.4650370463618525);
INSERT INTO public.city_connection VALUES (9, 1, 10, 146, 203, 0, 123, 0, 15.4, 6.7, 3, 0.854999997517834, 0.06095890402793885, 0.15, 0, 0.7659589015457727);
INSERT INTO public.city_connection VALUES (10, 1, 11, 108, 179, 0, 63, 0, 15.4, 28, 2, 0.7544444419719555, 0.034259259700775146, 0.1, 0, 0.6887037016727307);
INSERT INTO public.city_connection VALUES (3, 1, 4, 231, 260, 0, 212.1, 0, 0, 18, 1, 0.8653246991046064, 0.08744588792324066, 0.05, 0.031, 0.871770587027847);
INSERT INTO public.city_connection VALUES (249, 36, 38, 463, 0, 0, 393, 0, 38, 32, 5, 0.8559395248380129, 0, 0.25, 0.263, 0.34293952483801293);
INSERT INTO public.city_connection VALUES (250, 3, 7, 388, 0, 18, 0, 0, 193, 177, 1, 0.622680412371134, 0, 0.05, 0.188, 0.384680412371134);
INSERT INTO public.city_connection VALUES (251, 3, 12, 311, 0, 0, 0, 0, 133, 178, 1, 0.5855305466237942, 0, 0.05, 0.111, 0.4245305466237942);
INSERT INTO public.city_connection VALUES (252, 3, 13, 455, 0, 0, 0, 0, 125, 330, 4, 0.554945054945055, 0, 0.2, 0.255, 0.09994505494505496);
INSERT INTO public.city_connection VALUES (253, 3, 15, 268, 0, 0, 0, 0, 92, 176, 1, 0.5686567164179104, 0, 0.05, 0.068, 0.45065671641791033);
INSERT INTO public.city_connection VALUES (254, 3, 16, 425, 0, 0, 0, 0, 159, 266, 4, 0.5748235294117647, 0, 0.2, 0.225, 0.14982352941176472);
INSERT INTO public.city_connection VALUES (255, 3, 19, 172, 0, 0, 0, 0, 0, 172, 0, 0.5, 0, 0, 0, 0.5);
INSERT INTO public.city_connection VALUES (256, 3, 20, 330, 0, 0, 0, 0, 95, 235, 3, 0.5575757575757576, 0, 0.15, 0.13, 0.2775757575757576);
INSERT INTO public.city_connection VALUES (257, 3, 22, 289, 0, 18, 0, 0, 94, 177, 1, 0.5961937716262976, 0, 0.05, 0.089, 0.4571937716262976);
INSERT INTO public.city_connection VALUES (258, 3, 24, 47, 0, 0, 0, 0, 0, 47, 0, 0.5, 0, 0, 0, 0.5);
INSERT INTO public.city_connection VALUES (259, 3, 25, 379, 0, 0, 0, 0, 205, 174, 1, 0.6081794195250659, 0, 0.05, 0.179, 0.3791794195250659);
INSERT INTO public.city_connection VALUES (260, 3, 26, 390, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0.05, 0.19, -0.24);
INSERT INTO public.city_connection VALUES (261, 3, 28, 133, 0, 0, 0, 0, 0, 133, 0, 0.5, 0, 0, 0, 0.5);
INSERT INTO public.city_connection VALUES (262, 3, 29, 385, 0, 0, 0, 0, 112, 273, 4, 0.5581818181818181, 0, 0.2, 0.185, 0.1731818181818181);
INSERT INTO public.city_connection VALUES (263, 3, 30, 244, 0, 0, 0, 0, 2, 242, 1, 0.5016393442622951, 0, 0.05, 0.044, 0.4076393442622951);
INSERT INTO public.city_connection VALUES (264, 3, 34, 208, 0, 0, 0, 0, 20, 188, 1, 0.5192307692307693, 0, 0.05, 0.008, 0.4612307692307693);
INSERT INTO public.city_connection VALUES (265, 3, 37, 208, 0, 0, 0, 0, 31, 177, 1, 0.5298076923076923, 0, 0.05, 0.008, 0.4718076923076923);
INSERT INTO public.city_connection VALUES (82, 6, 27, 102, 0, 0, 0, 0, 0, 102, 1, 0.5, 0, 0.05, 0, 0.45);
INSERT INTO public.city_connection VALUES (266, 3, 2, 251, 0, 0, 0, 0, 72, 179, 1, 0.5573705179282868, 0, 0.05, 0.051000000000000004, 0.4563705179282868);
INSERT INTO public.city_connection VALUES (267, 7, 12, 159, 0, 0, 0, 0, 59, 100, 1, 0.5742138364779874, 0, 0.05, 0, 0.5242138364779874);
INSERT INTO public.city_connection VALUES (268, 7, 13, 94, 0, 0, 0, 0, 0, 94, 0, 0.5, 0, 0, 0, 0.5);
INSERT INTO public.city_connection VALUES (19, 1, 20, 226, 245, 0, 0, 0, 201.8, 25.4, 0, 0.681238946661485, 0.09159292057156564, 0, 0.026000000000000002, 0.7468318672330506);
INSERT INTO public.city_connection VALUES (11, 1, 12, 225, 281, 0, 0, 0, 224.1, 1.2, 1, 0.6998666857613457, 0.07511111050844194, 0.05, 0.025, 0.6999777962697876);
INSERT INTO public.city_connection VALUES (12, 1, 13, 257, 272, 0, 0, 0, 168.2, 89.6, 2, 0.6324513505868875, 0.09416342414915563, 0.1, 0.057, 0.5696147747360432);
INSERT INTO public.city_connection VALUES (4, 1, 5, 96, 100, 0, 93.3, 0, 0, 2.7, 0, 0.8887500288585822, 0.09583333320915699, 0, 0, 0.9845833620677392);
INSERT INTO public.city_connection VALUES (284, 12, 15, 47, 0, 0, 0, 0, 41, 6, 1, 0.674468085106383, 0, 0.05, 0, 0.624468085106383);
INSERT INTO public.city_connection VALUES (285, 12, 16, 158, 0, 0, 0, 0, 49, 109, 3, 0.5620253164556962, 0, 0.15, 0, 0.4120253164556962);
INSERT INTO public.city_connection VALUES (286, 12, 19, 174, 0, 0, 0, 0, 134, 40, 2, 0.6540229885057471, 0, 0.1, 0, 0.5540229885057472);
INSERT INTO public.city_connection VALUES (287, 12, 20, 103, 0, 0, 0, 0, 38, 65, 3, 0.5737864077669902, 0, 0.15, 0, 0.42378640776699017);
INSERT INTO public.city_connection VALUES (288, 12, 22, 216, 0, 0, 0, 0, 111, 105, 1, 0.6027777777777777, 0, 0.05, 0.016, 0.5367777777777777);
INSERT INTO public.city_connection VALUES (236, 23, 33, 266, 0, 133, 47, 0, 43, 43, 1, 0.8530075187969925, 0, 0.05, 0.066, 0.7370075187969924);
INSERT INTO public.city_connection VALUES (237, 23, 35, 94, 0, 0, 49, 0, 0, 45, 1, 0.7085106382978723, 0, 0.05, 0, 0.6585106382978723);
INSERT INTO public.city_connection VALUES (238, 23, 36, 268, 0, 0, 108, 0, 38, 122, 3, 0.6895522388059702, 0, 0.15, 0.068, 0.47155223880597014);
INSERT INTO public.city_connection VALUES (239, 23, 38, 213, 0, 0, 126, 0, 0, 87, 1, 0.7366197183098592, 0, 0.05, 0.013000000000000001, 0.6736197183098591);
INSERT INTO public.city_connection VALUES (240, 32, 33, 197, 0, 49, 0, 0, 148, 0, 1, 0.7746192893401015, 0, 0.05, 0, 0.7246192893401014);
INSERT INTO public.city_connection VALUES (241, 32, 35, 162, 0, 0, 152, 0, 8, 2, 0, 0.8851851851851852, 0, 0, 0, 0.8851851851851852);
INSERT INTO public.city_connection VALUES (242, 32, 36, 71, 0, 0, 0, 0, 46, 25, 3, 0.6295774647887323, 0, 0.15, 0, 0.4795774647887323);
INSERT INTO public.city_connection VALUES (243, 32, 38, 407, 0, 0, 391, 0, 8, 8, 2, 0.8882063882063883, 0, 0.1, 0.20700000000000002, 0.5812063882063883);
INSERT INTO public.city_connection VALUES (244, 33, 35, 173, 0, 109, 5, 0, 43, 16, 0, 0.8763005780346821, 0, 0, 0, 0.8763005780346821);
INSERT INTO public.city_connection VALUES (245, 33, 36, 176, 0, 49, 0, 0, 102, 25, 2, 0.7551136363636363, 0, 0.1, 0, 0.6551136363636363);
INSERT INTO public.city_connection VALUES (246, 33, 38, 421, 0, 133, 237, 0, 43, 8, 2, 0.903562945368171, 0, 0.1, 0.221, 0.5825629453681711);
INSERT INTO public.city_connection VALUES (247, 35, 36, 217, 0, 0, 152, 0, 38, 27, 3, 0.8152073732718894, 0, 0.15, 0.017, 0.6482073732718894);
INSERT INTO public.city_connection VALUES (402, 34, 37, 67, 0, 0, 0, 0, 51, 16, 1, 0.6522388059701492, 0, 0.05, 0, 0.6022388059701491);
INSERT INTO public.city_connection VALUES (403, 34, 2, 71, 0, 0, 0, 0, 53, 18, 0, 0.6492957746478872, 0, 0, 0, 0.6492957746478872);
INSERT INTO public.city_connection VALUES (404, 37, 2, 111, 0, 0, 0, 0, 104, 7, 1, 0.6873873873873874, 0, 0.05, 0, 0.6373873873873873);
INSERT INTO public.city_connection VALUES (248, 35, 38, 249, 0, 0, 239, 0, 0, 10, 2, 0.8839357429718875, 0, 0.1, 0.049, 0.7349357429718875);
INSERT INTO public.city_connection VALUES (70, 5, 35, 57, 0, 0, 53, 0, 0, 4, 0, 0.8719298245614036, 0, 0, 0, 0.8719298245614036);
INSERT INTO public.city_connection VALUES (71, 5, 36, 165, 0, 0, 99, 0, 38, 28, 3, 0.786060606060606, 0, 0.15, 0, 0.636060606060606);
INSERT INTO public.city_connection VALUES (72, 5, 38, 303, 0, 0, 291, 0, 0, 12, 2, 0.8841584158415843, 0, 0.1, 0.10300000000000001, 0.6811584158415843);
INSERT INTO public.city_connection VALUES (73, 6, 8, 311, 0, 182, 47, 0, 0, 82, 1, 0.8530546623794213, 0, 0.05, 0.111, 0.6920546623794213);
INSERT INTO public.city_connection VALUES (74, 6, 9, 36, 0, 0, 0, 0, 0, 36, 1, 0.5, 0, 0.05, 0, 0.45);
INSERT INTO public.city_connection VALUES (75, 6, 10, 91, 0, 0, 46, 0, 0, 45, 1, 0.7021978021978021, 0, 0.05, 0, 0.6521978021978021);
INSERT INTO public.city_connection VALUES (76, 6, 11, 80, 0, 0, 14, 0, 0, 66, 0, 0.5700000000000001, 0, 0, 0, 0.5700000000000001);
INSERT INTO public.city_connection VALUES (77, 6, 14, 197, 0, 0, 58, 0, 0, 139, 1, 0.6177664974619289, 0, 0.05, 0, 0.5677664974619289);
INSERT INTO public.city_connection VALUES (78, 6, 17, 216, 0, 97, 47, 0, 0, 72, 1, 0.8115740740740741, 0, 0.05, 0.016, 0.7455740740740741);
INSERT INTO public.city_connection VALUES (79, 6, 18, 178, 0, 0, 103, 0, 0, 75, 1, 0.7314606741573033, 0, 0.05, 0, 0.6814606741573033);
INSERT INTO public.city_connection VALUES (80, 6, 21, 189, 0, 72, 47, 0, 0, 70, 1, 0.78994708994709, 0, 0.05, 0, 0.7399470899470899);
INSERT INTO public.city_connection VALUES (83, 6, 31, 353, 0, 182, 47, 0, 6, 118, 2, 0.8144475920679887, 0, 0.1, 0.153, 0.5614475920679887);
INSERT INTO public.city_connection VALUES (84, 6, 32, 253, 0, 0, 108, 0, 9, 136, 1, 0.6778656126482213, 0, 0.05, 0.053, 0.5748656126482212);
INSERT INTO public.city_connection VALUES (85, 6, 33, 292, 0, 133, 47, 0, 0, 112, 1, 0.792123287671233, 0, 0.05, 0.092, 0.650123287671233);
INSERT INTO public.city_connection VALUES (86, 6, 35, 120, 0, 0, 49, 0, 0, 71, 1, 0.6633333333333333, 0, 0.05, 0, 0.6133333333333333);
INSERT INTO public.city_connection VALUES (87, 6, 36, 309, 0, 0, 108, 0, 38, 163, 4, 0.6644012944983819, 0, 0.2, 0.109, 0.3554012944983819);
INSERT INTO public.city_connection VALUES (88, 6, 38, 172, 0, 0, 126, 0, 0, 46, 1, 0.7930232558139535, 0, 0.05, 0, 0.7430232558139535);
INSERT INTO public.city_connection VALUES (89, 8, 9, 347, 0, 182, 47, 0, 6, 112, 1, 0.8198847262247838, 0, 0.05, 0.147, 0.6228847262247837);
INSERT INTO public.city_connection VALUES (90, 8, 10, 358, 0, 182, 156, 0, 6, 14, 1, 0.9318435754189943, 0, 0.05, 0.158, 0.7238435754189942);
INSERT INTO public.city_connection VALUES (91, 8, 11, 319, 0, 182, 96, 0, 6, 35, 1, 0.9094043887147334, 0, 0.05, 0.11900000000000001, 0.7404043887147334);
INSERT INTO public.city_connection VALUES (93, 8, 17, 99, 0, 86, 0, 0, 6, 7, 0, 0.9464646464646465, 0, 0, 0, 0.9464646464646465);
INSERT INTO public.city_connection VALUES (94, 8, 18, 255, 0, 182, 56, 0, 6, 11, 0, 0.9494117647058823, 0, 0, 0.055, 0.8944117647058822);
INSERT INTO public.city_connection VALUES (95, 8, 21, 122, 0, 110, 0, 0, 6, 6, 0, 0.9606557377049181, 0, 0, 0, 0.9606557377049181);
INSERT INTO public.city_connection VALUES (96, 8, 23, 284, 0, 182, 47, 0, 6, 49, 1, 0.8908450704225352, 0, 0.05, 0.084, 0.7568450704225352);
INSERT INTO public.city_connection VALUES (97, 8, 27, 281, 0, 158, 74, 0, 7, 42, 1, 0.8914590747330962, 0, 0.05, 0.081, 0.7604590747330962);
INSERT INTO public.city_connection VALUES (98, 8, 31, 42, 0, 0, 0, 0, 0, 42, 1, 0.5, 0, 0.05, 0, 0.45);
INSERT INTO public.city_connection VALUES (99, 8, 32, 105, 0, 0, 0, 0, 99, 6, 1, 0.6885714285714285, 0, 0.05, 0, 0.6385714285714285);
INSERT INTO public.city_connection VALUES (61, 5, 14, 54, 0, 0, 49, 0, 0, 5, 0, 0.862962962962963, 0, 0, 0, 0.862962962962963);
INSERT INTO public.city_connection VALUES (92, 8, 14, 147, 0, 0, 48, 0, 91, 8, 1, 0.754421768707483, 0, 0.05, 0, 0.704421768707483);
INSERT INTO public.city_connection VALUES (13, 1, 14, 144, 348, 0, 124.1, 0, 19.3, 0.3, 1, 0.8704860979070266, 0, 0.05, 0, 0.8204860979070265);
INSERT INTO public.city_connection VALUES (274, 7, 24, 341, 0, 18, 0, 0, 193, 130, 1, 0.6395894428152492, 0, 0.05, 0.14100000000000001, 0.44858944281524915);
INSERT INTO public.city_connection VALUES (275, 7, 25, 227, 0, 0, 0, 0, 131, 96, 0, 0.6154185022026432, 0, 0, 0.027, 0.5884185022026431);
INSERT INTO public.city_connection VALUES (276, 7, 26, 200, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0.05, 0, -0.05);
INSERT INTO public.city_connection VALUES (277, 7, 28, 297, 0, 0, 0, 0, 193, 86, 1, 0.5996632996632997, 0, 0.05, 0.097, 0.45266329966329966);
INSERT INTO public.city_connection VALUES (278, 7, 29, 86, 0, 0, 0, 0, 70, 16, 2, 0.6627906976744186, 0, 0.1, 0, 0.5627906976744186);
INSERT INTO public.city_connection VALUES (279, 7, 30, 295, 0, 18, 0, 0, 215, 62, 0, 0.676271186440678, 0, 0, 0.095, 0.581271186440678);
INSERT INTO public.city_connection VALUES (280, 7, 34, 164, 0, 0, 0, 0, 94, 70, 0, 0.6146341463414634, 0, 0, 0, 0.6146341463414634);
INSERT INTO public.city_connection VALUES (281, 7, 37, 177, 0, 0, 0, 0, 79, 98, 1, 0.5892655367231638, 0, 0.05, 0, 0.5392655367231638);
INSERT INTO public.city_connection VALUES (282, 7, 2, 151, 0, 0, 0, 0, 140, 11, 0, 0.6854304635761589, 0, 0, 0, 0.6854304635761589);
INSERT INTO public.city_connection VALUES (100, 8, 33, 104, 0, 49, 0, 0, 49, 6, 0, 0.8298076923076922, 0, 0, 0, 0.8298076923076922);
INSERT INTO public.city_connection VALUES (14, 1, 15, 182, 238, 0, 0, 0, 182.2, 0.2, 0, 0.7013186695893388, 0.06923076808452606, 0, 0, 0.7705494376738649);
INSERT INTO public.city_connection VALUES (20, 1, 21, 91, 0, 76, 0, 0, 14, 0, 0, 0.9428571428571428, 0, 0, 0, 0.9428571428571428);
INSERT INTO public.city_connection VALUES (21, 1, 22, 63, 154, 0, 0, 0, 63, 0, 0, 0.7, 0, 0, 0, 0.7);
INSERT INTO public.city_connection VALUES (22, 1, 23, 73, 118, 0, 14, 0, 16.5, 42.1, 0, 0.6191780717405554, 0.03835616707801819, 0, 0, 0.6575342388185735);
INSERT INTO public.city_connection VALUES (23, 1, 24, 215, 0, 0, 0, 0, 88.5, 126, 1, 0.5811627906976744, 0, 0.05, 0.015, 0.5161627906976743);
INSERT INTO public.city_connection VALUES (24, 1, 25, 294, 0, 0, 0, 0, 294, 0, 0, 0.7, 0, 0, 0.094, 0.606);
INSERT INTO public.city_connection VALUES (25, 1, 26, 351, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0.05, 0.151, -0.201);
INSERT INTO public.city_connection VALUES (26, 1, 27, 106, 134, 0, 83.6, 0, 22.2, 0, 1, 0.8564150864223263, 0.07358490526676179, 0.05, 0, 0.879999991689088);
INSERT INTO public.city_connection VALUES (27, 1, 28, 171, 0, 0, 0, 0, 88.5, 82, 1, 0.6020467836257309, 0, 0.05, 0, 0.5520467836257309);
INSERT INTO public.city_connection VALUES (28, 1, 29, 231, 261, 0, 0, 0, 221.2, 11, 2, 0.6941125448648031, 0.0870129868388176, 0.1, 0.031, 0.6501255317036208);
INSERT INTO public.city_connection VALUES (29, 1, 30, 104, 197, 0, 0, 0, 104, 0, 0, 0.7, 0.010576921701431277, 0, 0, 0.7105769217014313);
INSERT INTO public.city_connection VALUES (30, 1, 31, 225, 332, 186, 5.7, 0, 19.9, 42, 0, 1.004711109161377, 0.052444443106651306, 0, 0.025, 1.0321555522680284);
INSERT INTO public.city_connection VALUES (31, 1, 32, 200, 275, 0, 174.1, 0, 25.9, 0, 1, 0.8741000261306763, 0.0625, 0.05, 0, 0.8866000261306762);
INSERT INTO public.city_connection VALUES (32, 1, 33, 194, 395, 137, 43, 0, 14, 0, 0, 0.9561855670103093, 0, 0, 0, 0.9561855670103093);
INSERT INTO public.city_connection VALUES (33, 1, 34, 83, 133, 0, 0, 0, 69, 14, 0, 0.6662650602409638, 0.03975903391838074, 0, 0, 0.7060240941593445);
INSERT INTO public.city_connection VALUES (34, 1, 35, 42, 47, 0, 22.1, 0, 0, 19.5, 2, 0.705714293888637, 0.08809523805975915, 0.1, 0, 0.6938095319483962);
INSERT INTO public.city_connection VALUES (35, 1, 36, 256, 285, 0, 174.1, 0, 56, 25, 3, 0.8140234589576721, 0.08867187500000001, 0.15, 0.056, 0.6966953339576721);
INSERT INTO public.city_connection VALUES (36, 1, 37, 122, 200, 0, 0, 0, 120.7, 1.3, 1, 0.6978688347535055, 0.03606557250022889, 0.05, 0, 0.6839344072537343);
INSERT INTO public.city_connection VALUES (37, 1, 38, 227, 238, 0, 203, 0, 17.7, 5.3, 2, 0.8711013243587007, 0.09515418484807014, 0.1, 0.027, 0.8392555092067708);
INSERT INTO public.city_connection VALUES (56, 5, 6, 143, 0, 0, 9, 0, 0, 134, 1, 0.5251748251748252, 0, 0.05, 0, 0.4751748251748252);
INSERT INTO public.city_connection VALUES (57, 5, 8, 215, 0, 86, 49, 0, 70, 10, 0, 0.8562790697674418, 0, 0, 0.015, 0.8412790697674418);
INSERT INTO public.city_connection VALUES (58, 5, 9, 179, 0, 0, 9, 0, 0, 170, 2, 0.5201117318435754, 0, 0.1, 0, 0.4201117318435754);
INSERT INTO public.city_connection VALUES (59, 5, 10, 222, 0, 0, 212, 0, 0, 10, 1, 0.8819819819819821, 0, 0.05, 0.022, 0.809981981981982);
INSERT INTO public.city_connection VALUES (60, 5, 11, 183, 0, 0, 152, 0, 0, 31, 1, 0.83224043715847, 0, 0.05, 0, 0.7822404371584699);
INSERT INTO public.city_connection VALUES (119, 31, 10, 400, 0, 182, 163, 0, 0, 55, 3, 0.8905000000000001, 0, 0.15, 0.2, 0.5405);
INSERT INTO public.city_connection VALUES (62, 5, 17, 116, 0, 0, 49, 0, 64, 3, 0, 0.7793103448275862, 0, 0, 0, 0.7793103448275862);
INSERT INTO public.city_connection VALUES (38, 4, 5, 140, 0, 0, 137, 0, 0, 3, 1, 0.8914285714285715, 0, 0.05, 0, 0.8414285714285714);
INSERT INTO public.city_connection VALUES (39, 4, 6, 283, 0, 0, 146, 0, 0, 137, 2, 0.7063604240282686, 0, 0.1, 0.083, 0.5233604240282687);
INSERT INTO public.city_connection VALUES (40, 4, 8, 59, 0, 0, 53, 0, 0, 6, 1, 0.8593220338983051, 0, 0.05, 0, 0.809322033898305);
INSERT INTO public.city_connection VALUES (41, 4, 9, 319, 0, 0, 146, 0, 0, 173, 2, 0.6830721003134796, 0, 0.1, 0.11900000000000001, 0.4640721003134797);
INSERT INTO public.city_connection VALUES (42, 4, 10, 356, 0, 0, 349, 0, 0, 7, 3, 0.8921348314606742, 0, 0.15, 0.156, 0.5861348314606741);
INSERT INTO public.city_connection VALUES (43, 4, 11, 318, 0, 0, 290, 0, 0, 28, 2, 0.8647798742138365, 0, 0.1, 0.11800000000000001, 0.6467798742138365);
INSERT INTO public.city_connection VALUES (44, 4, 14, 88, 0, 0, 86, 0, 0, 2, 1, 0.890909090909091, 0, 0.05, 0, 0.8409090909090909);
INSERT INTO public.city_connection VALUES (45, 4, 17, 147, 0, 86, 56, 0, 0, 2, 2, 0.9346938775510204, 0, 0.1, 0, 0.8346938775510204);
INSERT INTO public.city_connection VALUES (46, 4, 18, 282, 0, 0, 276, 0, 0, 6, 1, 0.8914893617021277, 0, 0.05, 0.082, 0.7594893617021277);
INSERT INTO public.city_connection VALUES (47, 4, 21, 169, 0, 110, 56, 0, 0, 0, 1, 0.9491124260355029, 0, 0.05, 0, 0.8991124260355029);
INSERT INTO public.city_connection VALUES (48, 4, 23, 242, 0, 0, 146, 0, 0, 96, 2, 0.7413223140495868, 0, 0.1, 0.042, 0.5993223140495868);
INSERT INTO public.city_connection VALUES (49, 4, 27, 231, 0, 0, 181, 0, 0, 50, 3, 0.8134199134199135, 0, 0.15, 0.031, 0.6324199134199134);
INSERT INTO public.city_connection VALUES (50, 4, 31, 101, 0, 0, 53, 0, 0, 48, 2, 0.70990099009901, 0, 0.1, 0, 0.60990099009901);
INSERT INTO public.city_connection VALUES (51, 4, 32, 46, 0, 0, 46, 0, 0, 0, 1, 0.9, 0, 0.05, 0, 0.85);
INSERT INTO public.city_connection VALUES (52, 4, 33, 151, 0, 49, 102, 0, 0, 0, 1, 0.9324503311258279, 0, 0.05, 0, 0.8824503311258278);
INSERT INTO public.city_connection VALUES (53, 4, 35, 192, 0, 0, 190, 0, 0, 2, 1, 0.8958333333333334, 0, 0.05, 0, 0.8458333333333333);
INSERT INTO public.city_connection VALUES (54, 4, 36, 25, 0, 0, 0, 0, 0, 25, 2, 0.5, 0, 0.1, 0, 0.4);
INSERT INTO public.city_connection VALUES (63, 5, 18, 147, 0, 0, 138, 0, 0, 9, 0, 0.8755102040816326, 0, 0, 0, 0.8755102040816326);
INSERT INTO public.city_connection VALUES (55, 4, 38, 438, 0, 0, 430, 0, 0, 8, 3, 0.8926940639269406, 0, 0.15, 0.23800000000000002, 0.5046940639269406);
INSERT INTO public.city_connection VALUES (64, 5, 21, 113, 0, 48, 48, 0, 0, 17, 0, 0.8823008849557522, 0, 0, 0, 0.8823008849557522);
INSERT INTO public.city_connection VALUES (65, 5, 23, 102, 0, 0, 9, 0, 0, 93, 1, 0.5352941176470588, 0, 0.05, 0, 0.4852941176470588);
INSERT INTO public.city_connection VALUES (66, 5, 27, 91, 0, 0, 43, 0, 1, 47, 2, 0.6912087912087913, 0, 0.1, 0, 0.5912087912087913);
INSERT INTO public.city_connection VALUES (67, 5, 31, 258, 0, 86, 49, 0, 70, 53, 1, 0.7968992248062016, 0, 0.05, 0.058, 0.6888992248062015);
INSERT INTO public.city_connection VALUES (68, 5, 32, 110, 0, 0, 99, 0, 8, 3, 0, 0.8745454545454546, 0, 0, 0, 0.8745454545454546);
INSERT INTO public.city_connection VALUES (69, 5, 33, 197, 0, 36, 49, 0, 107, 5, 0, 0.799492385786802, 0, 0, 0, 0.799492385786802);
INSERT INTO public.city_connection VALUES (139, 9, 32, 289, 0, 0, 108, 0, 8, 173, 2, 0.6550173010380623, 0, 0.1, 0.089, 0.4660173010380624);
INSERT INTO public.city_connection VALUES (289, 12, 24, 263, 0, 0, 0, 0, 133, 130, 1, 0.6011406844106464, 0, 0.05, 0.063, 0.48814068441064634);
INSERT INTO public.city_connection VALUES (290, 12, 25, 76, 0, 0, 0, 0, 72, 4, 1, 0.6894736842105263, 0, 0.05, 0, 0.6394736842105263);
INSERT INTO public.city_connection VALUES (291, 12, 26, 180, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0.05, 0, -0.05);
INSERT INTO public.city_connection VALUES (292, 12, 28, 219, 0, 0, 0, 0, 133, 86, 2, 0.6214611872146119, 0, 0.1, 0.019, 0.5024611872146119);
INSERT INTO public.city_connection VALUES (293, 12, 29, 115, 0, 0, 0, 0, 3, 112, 3, 0.5052173913043478, 0, 0.15, 0, 0.3552173913043478);
INSERT INTO public.city_connection VALUES (294, 12, 30, 245, 0, 0, 0, 0, 135, 110, 3, 0.610204081632653, 0, 0.15, 0.045, 0.41520408163265304);
INSERT INTO public.city_connection VALUES (295, 12, 34, 170, 0, 0, 0, 0, 153, 17, 1, 0.6799999999999999, 0, 0.05, 0, 0.6299999999999999);
INSERT INTO public.city_connection VALUES (296, 12, 37, 108, 0, 0, 0, 0, 102, 6, 2, 0.6888888888888888, 0, 0.1, 0, 0.5888888888888888);
INSERT INTO public.city_connection VALUES (297, 12, 2, 214, 0, 0, 0, 0, 205, 9, 1, 0.6915887850467289, 0, 0.05, 0.014, 0.6275887850467289);
INSERT INTO public.city_connection VALUES (283, 12, 13, 188, 0, 0, 0, 0, 15, 173, 3, 0.5159574468085106, 0, 0.15, 0, 0.3659574468085106);
INSERT INTO public.city_connection VALUES (298, 13, 15, 192, 0, 0, 0, 0, 33, 159, 3, 0.5343749999999999, 0, 0.15, 0, 0.3843749999999999);
INSERT INTO public.city_connection VALUES (300, 13, 16, 85, 0, 0, 0, 0, 0, 85, 1, 0.5, 0, 0.05, 0, 0.45);
INSERT INTO public.city_connection VALUES (301, 13, 19, 318, 0, 0, 0, 0, 125, 193, 3, 0.5786163522012578, 0, 0.15, 0.11800000000000001, 0.31061635220125783);
INSERT INTO public.city_connection VALUES (302, 13, 20, 125, 0, 0, 0, 0, 30, 95, 1, 0.548, 0, 0.05, 0, 0.49800000000000005);
INSERT INTO public.city_connection VALUES (303, 13, 22, 192, 0, 0, 0, 0, 86, 106, 0, 0.5895833333333332, 0, 0, 0, 0.5895833333333332);
INSERT INTO public.city_connection VALUES (304, 13, 24, 408, 0, 0, 0, 0, 125, 283, 4, 0.5612745098039216, 0, 0.2, 0.20800000000000002, 0.15327450980392154);
INSERT INTO public.city_connection VALUES (305, 13, 25, 222, 0, 0, 0, 0, 5, 217, 0, 0.5045045045045045, 0, 0, 0.022, 0.48250450450450444);
INSERT INTO public.city_connection VALUES (306, 13, 26, 190, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0.05, 0, -0.05);
INSERT INTO public.city_connection VALUES (307, 13, 28, 364, 0, 0, 0, 0, 125, 239, 4, 0.5686813186813187, 0, 0.2, 0.164, 0.20468131868131864);
INSERT INTO public.city_connection VALUES (308, 13, 29, 74, 0, 0, 0, 0, 0, 74, 0, 0.5, 0, 0, 0, 0.5);
INSERT INTO public.city_connection VALUES (309, 13, 30, 353, 0, 18, 0, 0, 224, 111, 1, 0.6524079320113314, 0, 0.05, 0.153, 0.44940793201133133);
INSERT INTO public.city_connection VALUES (310, 13, 34, 257, 0, 0, 0, 0, 93, 164, 0, 0.5723735408560311, 0, 0, 0.057, 0.515373540856031);
INSERT INTO public.city_connection VALUES (311, 13, 37, 253, 0, 0, 0, 0, 94, 159, 3, 0.5743083003952569, 0, 0.15, 0.053, 0.3713083003952569);
INSERT INTO public.city_connection VALUES (312, 13, 2, 245, 0, 0, 0, 0, 140, 105, 0, 0.6142857142857143, 0, 0, 0.045, 0.5692857142857143);
INSERT INTO public.city_connection VALUES (131, 9, 10, 127, 0, 0, 46, 0, 0, 81, 2, 0.6448818897637796, 0, 0.1, 0, 0.5448818897637796);
INSERT INTO public.city_connection VALUES (269, 7, 15, 116, 0, 0, 0, 0, 18, 98, 0, 0.5310344827586208, 0, 0, 0, 0.5310344827586208);
INSERT INTO public.city_connection VALUES (270, 7, 16, 126, 0, 0, 0, 0, 117, 9, 1, 0.6857142857142856, 0, 0.05, 0, 0.6357142857142856);
INSERT INTO public.city_connection VALUES (271, 7, 19, 251, 0, 18, 0, 0, 193, 40, 2, 0.6896414342629482, 0, 0.1, 0.051000000000000004, 0.5386414342629482);
INSERT INTO public.city_connection VALUES (272, 7, 20, 81, 0, 0, 0, 0, 53, 28, 1, 0.6308641975308641, 0, 0.05, 0, 0.580864197530864);
INSERT INTO public.city_connection VALUES (273, 7, 22, 99, 0, 0, 0, 0, 87, 12, 0, 0.6757575757575758, 0, 0, 0, 0.6757575757575758);
INSERT INTO public.city_connection VALUES (5, 1, 6, 99, 158, 0, 14, 0, 15, 70, 1, 0.5868686868686869, 0.04040403962135315, 0.05, 0, 0.57727272649004);
INSERT INTO public.city_connection VALUES (18, 1, 19, 126, 172, 0, 0, 0, 88.5, 37, 1, 0.6384920634920634, 0.06349206268787384, 0.05, 0, 0.6519841261799373);


--
-- TOC entry 2869 (class 0 OID 262477)
-- Dependencies: 207
-- Data for Name: region; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.region VALUES (1, 'Саратовская обл', 1, 237, 1);


--
-- TOC entry 2905 (class 0 OID 0)
-- Dependencies: 206
-- Name: city_connection_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.city_connection_id_seq', 404, true);


--
-- TOC entry 2906 (class 0 OID 0)
-- Dependencies: 204
-- Name: city_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.city_id_seq', 39, true);


--
-- TOC entry 2907 (class 0 OID 0)
-- Dependencies: 208
-- Name: region_column1_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.region_column1_seq', 1, true);


--
-- TOC entry 2728 (class 2606 OID 262408)
-- Name: city_connection city_connection_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.city_connection
    ADD CONSTRAINT city_connection_pk PRIMARY KEY (id);


--
-- TOC entry 2724 (class 2606 OID 262375)
-- Name: city city_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.city
    ADD CONSTRAINT city_pk PRIMARY KEY (id);


--
-- TOC entry 2726 (class 2606 OID 262410)
-- Name: city city_un; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.city
    ADD CONSTRAINT city_un UNIQUE (full_name);


--
-- TOC entry 2731 (class 2606 OID 262490)
-- Name: region region_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.region
    ADD CONSTRAINT region_pk PRIMARY KEY (id);


--
-- TOC entry 2729 (class 1259 OID 262445)
-- Name: city_connection_un_collision; Type: INDEX; Schema: public; Owner: -
--

CREATE UNIQUE INDEX city_connection_un_collision ON public.city_connection USING btree (public.text_index(city_id_1, city_id_2));


--
-- TOC entry 2736 (class 2620 OID 262508)
-- Name: city update_city; Type: TRIGGER; Schema: public; Owner: -
--

CREATE TRIGGER update_city AFTER INSERT OR DELETE OR UPDATE ON public.city FOR EACH ROW EXECUTE FUNCTION public.trigger_calc_sum_of_machine();


--
-- TOC entry 2737 (class 2620 OID 262475)
-- Name: city_connection update_city_connection; Type: TRIGGER; Schema: public; Owner: -
--

CREATE TRIGGER update_city_connection BEFORE INSERT OR UPDATE ON public.city_connection FOR EACH ROW EXECUTE FUNCTION public.trigger_calc_coefficients_road_infrastructure();


--
-- TOC entry 2738 (class 2620 OID 262512)
-- Name: region update_region; Type: TRIGGER; Schema: public; Owner: -
--

CREATE TRIGGER update_region BEFORE INSERT OR UPDATE ON public.region FOR EACH ROW EXECUTE FUNCTION public.trigger_calc_sum_of_dealerships();


--
-- TOC entry 2734 (class 2606 OID 262421)
-- Name: city_connection city_connection_fk_1; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.city_connection
    ADD CONSTRAINT city_connection_fk_1 FOREIGN KEY (city_id_1) REFERENCES public.city(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 2733 (class 2606 OID 262416)
-- Name: city_connection city_connection_fk_2; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.city_connection
    ADD CONSTRAINT city_connection_fk_2 FOREIGN KEY (city_id_2) REFERENCES public.city(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 2732 (class 2606 OID 262501)
-- Name: city city_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.city
    ADD CONSTRAINT city_fk FOREIGN KEY (region_id) REFERENCES public.region(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 2735 (class 2606 OID 262496)
-- Name: region region_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.region
    ADD CONSTRAINT region_fk FOREIGN KEY (central_city_id) REFERENCES public.city(id) ON UPDATE CASCADE ON DELETE RESTRICT;


-- Completed on 2022-03-28 04:26:01

--
-- PostgreSQL database dump complete
--

