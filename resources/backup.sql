--
-- PostgreSQL database dump
--

-- Dumped from database version 16.6 (Ubuntu 16.6-1.pgdg24.04+1)
-- Dumped by pg_dump version 17.2 (Ubuntu 17.2-1.pgdg24.04+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
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
-- Name: tabemprunt; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tabemprunt (
    id_emprunt integer NOT NULL,
    id_membre integer,
    id_livre integer,
    date_emprunt date NOT NULL,
    date_retour_prevue_emprunt date NOT NULL
);


ALTER TABLE public.tabemprunt OWNER TO postgres;

--
-- Name: emprunt_idemprunt_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.emprunt_idemprunt_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.emprunt_idemprunt_seq OWNER TO postgres;

--
-- Name: emprunt_idemprunt_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.emprunt_idemprunt_seq OWNED BY public.tabemprunt.id_emprunt;


--
-- Name: tablivre; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tablivre (
    id_livre integer NOT NULL,
    titre_livre character varying(255) NOT NULL,
    auteur_livre character varying(255) NOT NULL,
    categorie_livre character varying(100),
    nombre_exemplaires_livre integer DEFAULT 1
);


ALTER TABLE public.tablivre OWNER TO postgres;

--
-- Name: livre_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.livre_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.livre_id_seq OWNER TO postgres;

--
-- Name: livre_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.livre_id_seq OWNED BY public.tablivre.id_livre;


--
-- Name: tabmembre; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tabmembre (
    id_membre integer NOT NULL,
    nom_membre character varying(100) NOT NULL,
    prenom_membre character varying(100) NOT NULL,
    email_membre character varying(255) NOT NULL,
    date_adhesion_membre date NOT NULL
);


ALTER TABLE public.tabmembre OWNER TO postgres;

--
-- Name: membre_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.membre_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.membre_id_seq OWNER TO postgres;

--
-- Name: membre_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.membre_id_seq OWNED BY public.tabmembre.id_membre;


--
-- Name: tabretour; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tabretour (
    id_retour integer NOT NULL,
    id_emprunt integer,
    date_effective_retour date NOT NULL,
    penalite_retour integer NOT NULL
);


ALTER TABLE public.tabretour OWNER TO postgres;

--
-- Name: penalite_idpenalite_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.penalite_idpenalite_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.penalite_idpenalite_seq OWNER TO postgres;

--
-- Name: penalite_idpenalite_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.penalite_idpenalite_seq OWNED BY public.tabretour.id_retour;


--
-- Name: tabemprunt id_emprunt; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tabemprunt ALTER COLUMN id_emprunt SET DEFAULT nextval('public.emprunt_idemprunt_seq'::regclass);


--
-- Name: tablivre id_livre; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tablivre ALTER COLUMN id_livre SET DEFAULT nextval('public.livre_id_seq'::regclass);


--
-- Name: tabmembre id_membre; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tabmembre ALTER COLUMN id_membre SET DEFAULT nextval('public.membre_id_seq'::regclass);


--
-- Name: tabretour id_retour; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tabretour ALTER COLUMN id_retour SET DEFAULT nextval('public.penalite_idpenalite_seq'::regclass);


--
-- Data for Name: tabemprunt; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tabemprunt (id_emprunt, id_membre, id_livre, date_emprunt, date_retour_prevue_emprunt) FROM stdin;
6	28	61	2025-01-18	2025-01-30
8	27	68	2025-01-20	2025-01-25
9	28	78	2025-01-22	2025-02-25
\.


--
-- Data for Name: tablivre; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tablivre (id_livre, titre_livre, auteur_livre, categorie_livre, nombre_exemplaires_livre) FROM stdin;
62	Les bases de données relationnelles	Paul MARTIN	Informatique	3
63	Les guides des algorithmes	Lemoine CLAIRE	Informatique	2
64	Les grands classiques de la littérature	Zola EMILE	Littérature	4
65	Introduction à la physique	Curie MARIE	Sciences	6
67	Histoire de la philosophie	Descarte RENE	Philosophie	2
70	Les mystres de l'univers	Hawking STEPHEN	Sciences	2
69	Le langage C	Rost CLAUDE	Informatique	5
72	le vent	cool	geographie	3
73	l'eclair	jeannot	geographie	7
77	histoires d'afrique	rostan ndié	contes	0
68	Le roman de l'histoire	Hugo VICTOR	Litterature	4
71	La famille	Raoul Tanefo	Education	11
61	Java pour débutant	Jean DUPONT	Informatique	21
78	Le questionnement	Dr Fokam Paul	Education	50
\.


--
-- Data for Name: tabmembre; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tabmembre (id_membre, nom_membre, prenom_membre, email_membre, date_adhesion_membre) FROM stdin;
23	martin	alice	alice@exp.com	2025-01-16
24	dupont	louis	louidupon@exemple.com	2025-01-16
25	clarisse	nanfou	nanclar@ex.com	2025-01-16
27	honore	gomda	hgomd@expl.com	2025-01-16
28	ndie fonkou	claude rostan	mailrostan@gmail.com	2025-01-17
29	jean bernard	dadier	jdjdd@jdjd.fr	2025-01-17
32	Jumbum	Junior	juju@exemple.com	2025-01-22
\.


--
-- Data for Name: tabretour; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tabretour (id_retour, id_emprunt, date_effective_retour, penalite_retour) FROM stdin;
2	6	2025-02-09	1000
3	8	2025-01-31	600
4	6	2025-01-31	100
5	9	2025-02-26	100
\.


--
-- Name: emprunt_idemprunt_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.emprunt_idemprunt_seq', 9, true);


--
-- Name: livre_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.livre_id_seq', 78, true);


--
-- Name: membre_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.membre_id_seq', 32, true);


--
-- Name: penalite_idpenalite_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.penalite_idpenalite_seq', 5, true);


--
-- Name: tabemprunt emprunt_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tabemprunt
    ADD CONSTRAINT emprunt_pkey PRIMARY KEY (id_emprunt);


--
-- Name: tablivre livre_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tablivre
    ADD CONSTRAINT livre_pkey PRIMARY KEY (id_livre);


--
-- Name: tabmembre membre_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tabmembre
    ADD CONSTRAINT membre_email_key UNIQUE (email_membre);


--
-- Name: tabmembre membre_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tabmembre
    ADD CONSTRAINT membre_pkey PRIMARY KEY (id_membre);


--
-- Name: tabretour penalite_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tabretour
    ADD CONSTRAINT penalite_pkey PRIMARY KEY (id_retour);


--
-- Name: tabemprunt emprunt_livreid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tabemprunt
    ADD CONSTRAINT emprunt_livreid_fkey FOREIGN KEY (id_livre) REFERENCES public.tablivre(id_livre) ON DELETE CASCADE;


--
-- Name: tabemprunt emprunt_membreid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tabemprunt
    ADD CONSTRAINT emprunt_membreid_fkey FOREIGN KEY (id_membre) REFERENCES public.tabmembre(id_membre) ON DELETE CASCADE;


--
-- Name: tabretour penalite_empruntid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tabretour
    ADD CONSTRAINT penalite_empruntid_fkey FOREIGN KEY (id_emprunt) REFERENCES public.tabemprunt(id_emprunt) ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

