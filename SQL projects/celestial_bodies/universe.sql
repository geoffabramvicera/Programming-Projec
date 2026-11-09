--
-- PostgreSQL database dump
--

-- Dumped from database version 12.22 (Ubuntu 12.22-0ubuntu0.20.04.4)
-- Dumped by pg_dump version 12.22 (Ubuntu 12.22-0ubuntu0.20.04.4)

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

DROP DATABASE universe;
--
-- Name: universe; Type: DATABASE; Schema: -; Owner: freecodecamp
--

CREATE DATABASE universe WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'C.UTF-8' LC_CTYPE = 'C.UTF-8';


ALTER DATABASE universe OWNER TO freecodecamp;

\connect universe

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: asteroid; Type: TABLE; Schema: public; Owner: freecodecamp
--

CREATE TABLE public.asteroid (
    asteroid_id integer NOT NULL,
    name character varying(100) NOT NULL,
    diameter_in_km integer NOT NULL
);


ALTER TABLE public.asteroid OWNER TO freecodecamp;

--
-- Name: asteroid_asteroid_id_seq; Type: SEQUENCE; Schema: public; Owner: freecodecamp
--

CREATE SEQUENCE public.asteroid_asteroid_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.asteroid_asteroid_id_seq OWNER TO freecodecamp;

--
-- Name: asteroid_asteroid_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: freecodecamp
--

ALTER SEQUENCE public.asteroid_asteroid_id_seq OWNED BY public.asteroid.asteroid_id;


--
-- Name: galaxy; Type: TABLE; Schema: public; Owner: freecodecamp
--

CREATE TABLE public.galaxy (
    galaxy_id integer NOT NULL,
    name character varying(100) NOT NULL,
    pangint integer NOT NULL,
    pangnumeric numeric NOT NULL,
    pangtext text NOT NULL,
    pangboolean boolean NOT NULL
);


ALTER TABLE public.galaxy OWNER TO freecodecamp;

--
-- Name: galaxy_galaxy_id_seq; Type: SEQUENCE; Schema: public; Owner: freecodecamp
--

CREATE SEQUENCE public.galaxy_galaxy_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.galaxy_galaxy_id_seq OWNER TO freecodecamp;

--
-- Name: galaxy_galaxy_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: freecodecamp
--

ALTER SEQUENCE public.galaxy_galaxy_id_seq OWNED BY public.galaxy.galaxy_id;


--
-- Name: moon; Type: TABLE; Schema: public; Owner: freecodecamp
--

CREATE TABLE public.moon (
    moon_id integer NOT NULL,
    name character varying(100) NOT NULL,
    planet_id integer NOT NULL,
    pangint integer NOT NULL,
    pangnumeric numeric NOT NULL,
    pangtext text NOT NULL,
    pangboolean boolean NOT NULL
);


ALTER TABLE public.moon OWNER TO freecodecamp;

--
-- Name: moon_moon_id_seq; Type: SEQUENCE; Schema: public; Owner: freecodecamp
--

CREATE SEQUENCE public.moon_moon_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.moon_moon_id_seq OWNER TO freecodecamp;

--
-- Name: moon_moon_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: freecodecamp
--

ALTER SEQUENCE public.moon_moon_id_seq OWNED BY public.moon.moon_id;


--
-- Name: planet; Type: TABLE; Schema: public; Owner: freecodecamp
--

CREATE TABLE public.planet (
    planet_id integer NOT NULL,
    name character varying(100) NOT NULL,
    star_id integer NOT NULL,
    pangint integer NOT NULL,
    pangnumeric numeric NOT NULL,
    pangtext text NOT NULL,
    pangboolean boolean NOT NULL
);


ALTER TABLE public.planet OWNER TO freecodecamp;

--
-- Name: planet_planet_id_seq; Type: SEQUENCE; Schema: public; Owner: freecodecamp
--

CREATE SEQUENCE public.planet_planet_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.planet_planet_id_seq OWNER TO freecodecamp;

--
-- Name: planet_planet_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: freecodecamp
--

ALTER SEQUENCE public.planet_planet_id_seq OWNED BY public.planet.planet_id;


--
-- Name: star; Type: TABLE; Schema: public; Owner: freecodecamp
--

CREATE TABLE public.star (
    star_id integer NOT NULL,
    name character varying(100) NOT NULL,
    galaxy_id integer NOT NULL,
    pangint integer NOT NULL,
    pangnumeric numeric NOT NULL,
    pangtext text NOT NULL,
    pangboolean boolean NOT NULL
);


ALTER TABLE public.star OWNER TO freecodecamp;

--
-- Name: star_star_id_seq; Type: SEQUENCE; Schema: public; Owner: freecodecamp
--

CREATE SEQUENCE public.star_star_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.star_star_id_seq OWNER TO freecodecamp;

--
-- Name: star_star_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: freecodecamp
--

ALTER SEQUENCE public.star_star_id_seq OWNED BY public.star.star_id;


--
-- Name: asteroid asteroid_id; Type: DEFAULT; Schema: public; Owner: freecodecamp
--

ALTER TABLE ONLY public.asteroid ALTER COLUMN asteroid_id SET DEFAULT nextval('public.asteroid_asteroid_id_seq'::regclass);


--
-- Name: galaxy galaxy_id; Type: DEFAULT; Schema: public; Owner: freecodecamp
--

ALTER TABLE ONLY public.galaxy ALTER COLUMN galaxy_id SET DEFAULT nextval('public.galaxy_galaxy_id_seq'::regclass);


--
-- Name: moon moon_id; Type: DEFAULT; Schema: public; Owner: freecodecamp
--

ALTER TABLE ONLY public.moon ALTER COLUMN moon_id SET DEFAULT nextval('public.moon_moon_id_seq'::regclass);


--
-- Name: planet planet_id; Type: DEFAULT; Schema: public; Owner: freecodecamp
--

ALTER TABLE ONLY public.planet ALTER COLUMN planet_id SET DEFAULT nextval('public.planet_planet_id_seq'::regclass);


--
-- Name: star star_id; Type: DEFAULT; Schema: public; Owner: freecodecamp
--

ALTER TABLE ONLY public.star ALTER COLUMN star_id SET DEFAULT nextval('public.star_star_id_seq'::regclass);


--
-- Data for Name: asteroid; Type: TABLE DATA; Schema: public; Owner: freecodecamp
--

INSERT INTO public.asteroid VALUES (1, 'Ceres', 939);
INSERT INTO public.asteroid VALUES (2, 'Vesta', 525);
INSERT INTO public.asteroid VALUES (3, 'Pallas', 512);


--
-- Data for Name: galaxy; Type: TABLE DATA; Schema: public; Owner: freecodecamp
--

INSERT INTO public.galaxy VALUES (1, 'Milky Way', 13600, 1.0, 'Spiral galaxy', true);
INSERT INTO public.galaxy VALUES (2, 'Andromeda', 10000, 2.5, 'Large spiral', false);
INSERT INTO public.galaxy VALUES (3, 'Triangulum', 12000, 0.5, 'Small spiral', false);
INSERT INTO public.galaxy VALUES (4, 'Sombrero', 13000, 8.0, 'Unbarred spiral', false);
INSERT INTO public.galaxy VALUES (5, 'Centaurus A', 11000, 4.2, 'Lenticular galaxy', false);
INSERT INTO public.galaxy VALUES (6, 'Whirlpool', 12500, 3.1, 'Grand design spiral', false);


--
-- Data for Name: moon; Type: TABLE DATA; Schema: public; Owner: freecodecamp
--

INSERT INTO public.moon VALUES (1, 'Moon', 3, 1737, 0.27, 'Earth companion', true);
INSERT INTO public.moon VALUES (2, 'Phobos', 4, 11, 0.001, 'Mars inner moon', true);
INSERT INTO public.moon VALUES (3, 'Deimos', 4, 6, 0.0005, 'Mars outer moon', true);
INSERT INTO public.moon VALUES (4, 'Io', 5, 1821, 0.28, 'Volcanic moon', true);
INSERT INTO public.moon VALUES (5, 'Europa', 5, 1560, 0.24, 'Icy ocean moon', true);
INSERT INTO public.moon VALUES (6, 'Ganymede', 5, 2634, 0.41, 'Largest moon', true);
INSERT INTO public.moon VALUES (7, 'Callisto', 5, 2410, 0.37, 'Cratered moon', true);
INSERT INTO public.moon VALUES (8, 'Mimas', 6, 198, 0.03, 'Small icy moon', true);
INSERT INTO public.moon VALUES (9, 'Enceladus', 6, 252, 0.04, 'Geyser moon', true);
INSERT INTO public.moon VALUES (10, 'Tethys', 6, 531, 0.08, 'Large icy moon', true);
INSERT INTO public.moon VALUES (11, 'Dione', 6, 561, 0.09, 'Heavy cratered moon', true);
INSERT INTO public.moon VALUES (12, 'Rhea', 6, 763, 0.12, 'Ruled by ice', true);
INSERT INTO public.moon VALUES (13, 'Titan', 6, 2574, 0.40, 'Thick atmosphere moon', true);
INSERT INTO public.moon VALUES (14, 'Hyperion', 6, 135, 0.02, 'Sponge appearance', true);
INSERT INTO public.moon VALUES (15, 'Iapetus', 6, 734, 0.11, 'Two toned moon', true);
INSERT INTO public.moon VALUES (16, 'Phoebe', 6, 106, 0.01, 'Retrograde orbit', true);
INSERT INTO public.moon VALUES (17, 'Janus', 6, 89, 0.01, 'Co orbital moon', true);
INSERT INTO public.moon VALUES (18, 'Epimetheus', 6, 58, 0.008, 'Co orbital companion', true);
INSERT INTO public.moon VALUES (19, 'Helene', 6, 17, 0.002, 'Trojan moon', true);
INSERT INTO public.moon VALUES (20, 'Telesto', 6, 12, 0.001, 'Tethys trojan', true);


--
-- Data for Name: planet; Type: TABLE DATA; Schema: public; Owner: freecodecamp
--

INSERT INTO public.planet VALUES (1, 'Mercury', 1, 0, 0.38, 'Terrestrial planet', false);
INSERT INTO public.planet VALUES (2, 'Venus', 1, 0, 0.95, 'Thick atmosphere', false);
INSERT INTO public.planet VALUES (3, 'Earth', 1, 1, 1.00, 'Water planet', true);
INSERT INTO public.planet VALUES (4, 'Mars', 1, 2, 0.53, 'Red desert planet', false);
INSERT INTO public.planet VALUES (5, 'Jupiter', 1, 95, 11.20, 'Gas giant', false);
INSERT INTO public.planet VALUES (6, 'Saturn', 1, 146, 9.45, 'Ringed gas giant', false);
INSERT INTO public.planet VALUES (7, 'Uranus', 1, 28, 4.01, 'Ice giant', false);
INSERT INTO public.planet VALUES (8, 'Neptune', 1, 16, 3.88, 'Windy ice giant', false);
INSERT INTO public.planet VALUES (9, 'Proxima b', 4, 0, 1.08, 'Exoplanet', false);
INSERT INTO public.planet VALUES (10, 'Sirius b', 2, 0, 0.01, 'White dwarf companion', false);
INSERT INTO public.planet VALUES (11, 'Vega Prime', 5, 3, 2.50, 'Fictional world', false);
INSERT INTO public.planet VALUES (12, 'Rigel Seven', 6, 5, 5.00, 'Outer planet', false);


--
-- Data for Name: star; Type: TABLE DATA; Schema: public; Owner: freecodecamp
--

INSERT INTO public.star VALUES (1, 'Sun', 1, 4600, 1.0, 'Yellow dwarf', true);
INSERT INTO public.star VALUES (2, 'Sirius', 1, 242, 2.1, 'Brightest star', false);
INSERT INTO public.star VALUES (3, 'Betelgeuse', 1, 10, 15.0, 'Red supergiant', false);
INSERT INTO public.star VALUES (4, 'Alpha Centauri', 1, 5000, 1.1, 'Closest system', false);
INSERT INTO public.star VALUES (5, 'Vega', 1, 450, 2.2, 'Northern star', false);
INSERT INTO public.star VALUES (6, 'Rigel', 1, 8000, 18.0, 'Blue supergiant', false);


--
-- Name: asteroid_asteroid_id_seq; Type: SEQUENCE SET; Schema: public; Owner: freecodecamp
--

SELECT pg_catalog.setval('public.asteroid_asteroid_id_seq', 3, true);


--
-- Name: galaxy_galaxy_id_seq; Type: SEQUENCE SET; Schema: public; Owner: freecodecamp
--

SELECT pg_catalog.setval('public.galaxy_galaxy_id_seq', 6, true);


--
-- Name: moon_moon_id_seq; Type: SEQUENCE SET; Schema: public; Owner: freecodecamp
--

SELECT pg_catalog.setval('public.moon_moon_id_seq', 20, true);


--
-- Name: planet_planet_id_seq; Type: SEQUENCE SET; Schema: public; Owner: freecodecamp
--

SELECT pg_catalog.setval('public.planet_planet_id_seq', 12, true);


--
-- Name: star_star_id_seq; Type: SEQUENCE SET; Schema: public; Owner: freecodecamp
--

SELECT pg_catalog.setval('public.star_star_id_seq', 6, true);


--
-- Name: asteroid asteroid_name_key; Type: CONSTRAINT; Schema: public; Owner: freecodecamp
--

ALTER TABLE ONLY public.asteroid
    ADD CONSTRAINT asteroid_name_key UNIQUE (name);


--
-- Name: asteroid asteroid_pkey; Type: CONSTRAINT; Schema: public; Owner: freecodecamp
--

ALTER TABLE ONLY public.asteroid
    ADD CONSTRAINT asteroid_pkey PRIMARY KEY (asteroid_id);


--
-- Name: galaxy galaxy_name_key; Type: CONSTRAINT; Schema: public; Owner: freecodecamp
--

ALTER TABLE ONLY public.galaxy
    ADD CONSTRAINT galaxy_name_key UNIQUE (name);


--
-- Name: galaxy galaxy_pkey; Type: CONSTRAINT; Schema: public; Owner: freecodecamp
--

ALTER TABLE ONLY public.galaxy
    ADD CONSTRAINT galaxy_pkey PRIMARY KEY (galaxy_id);


--
-- Name: moon moon_name_key; Type: CONSTRAINT; Schema: public; Owner: freecodecamp
--

ALTER TABLE ONLY public.moon
    ADD CONSTRAINT moon_name_key UNIQUE (name);


--
-- Name: moon moon_pkey; Type: CONSTRAINT; Schema: public; Owner: freecodecamp
--

ALTER TABLE ONLY public.moon
    ADD CONSTRAINT moon_pkey PRIMARY KEY (moon_id);


--
-- Name: planet planet_name_key; Type: CONSTRAINT; Schema: public; Owner: freecodecamp
--

ALTER TABLE ONLY public.planet
    ADD CONSTRAINT planet_name_key UNIQUE (name);


--
-- Name: planet planet_pkey; Type: CONSTRAINT; Schema: public; Owner: freecodecamp
--

ALTER TABLE ONLY public.planet
    ADD CONSTRAINT planet_pkey PRIMARY KEY (planet_id);


--
-- Name: star star_name_key; Type: CONSTRAINT; Schema: public; Owner: freecodecamp
--

ALTER TABLE ONLY public.star
    ADD CONSTRAINT star_name_key UNIQUE (name);


--
-- Name: star star_pkey; Type: CONSTRAINT; Schema: public; Owner: freecodecamp
--

ALTER TABLE ONLY public.star
    ADD CONSTRAINT star_pkey PRIMARY KEY (star_id);


--
-- Name: moon moon_planet_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: freecodecamp
--

ALTER TABLE ONLY public.moon
    ADD CONSTRAINT moon_planet_id_fkey FOREIGN KEY (planet_id) REFERENCES public.planet(planet_id);


--
-- Name: planet planet_star_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: freecodecamp
--

ALTER TABLE ONLY public.planet
    ADD CONSTRAINT planet_star_id_fkey FOREIGN KEY (star_id) REFERENCES public.star(star_id);


--
-- Name: star star_galaxy_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: freecodecamp
--

ALTER TABLE ONLY public.star
    ADD CONSTRAINT star_galaxy_id_fkey FOREIGN KEY (galaxy_id) REFERENCES public.galaxy(galaxy_id);


--
-- PostgreSQL database dump complete
--

