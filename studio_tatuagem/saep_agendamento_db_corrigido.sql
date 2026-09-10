--
-- PostgreSQL database dump
--

\restrict PubeFEMudh8QSPTnI7GydJcU85B5CiP2DXxc22mAhI6OpoGFKE9wil6XMJVdcFG

-- Dumped from database version 18.4
-- Dumped by pg_dump version 18.4

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
-- Name: agendamento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.agendamento (
    id integer NOT NULL,
    id_cliente integer NOT NULL,
    id_recurso integer NOT NULL,
    data date NOT NULL,
    hora time without time zone NOT NULL,
    observacao text
);


ALTER TABLE public.agendamento OWNER TO postgres;

--
-- Name: agendamento_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.agendamento_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.agendamento_id_seq OWNER TO postgres;

--
-- Name: agendamento_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.agendamento_id_seq OWNED BY public.agendamento.id;


--
-- Name: cliente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cliente (
    id integer NOT NULL,
    nome character varying(100) NOT NULL,
    documento character varying(20) NOT NULL,
    telefone character varying(20)
);


ALTER TABLE public.cliente OWNER TO postgres;

--
-- Name: cliente_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cliente_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.cliente_id_seq OWNER TO postgres;

--
-- Name: cliente_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cliente_id_seq OWNED BY public.cliente.id;


--
-- Name: recurso; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.recurso (
    id integer NOT NULL,
    nome character varying(100) NOT NULL,
    tipo character varying(50) NOT NULL
);


ALTER TABLE public.recurso OWNER TO postgres;

--
-- Name: recurso_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.recurso_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.recurso_id_seq OWNER TO postgres;

--
-- Name: recurso_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.recurso_id_seq OWNED BY public.recurso.id;


--
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario (
    id integer NOT NULL,
    nome character varying(100) NOT NULL,
    email character varying(100) NOT NULL,
    senha character varying(255) NOT NULL
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- Name: usuario_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.usuario_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.usuario_id_seq OWNER TO postgres;

--
-- Name: usuario_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.usuario_id_seq OWNED BY public.usuario.id;


--
-- Name: agendamento id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.agendamento ALTER COLUMN id SET DEFAULT nextval('public.agendamento_id_seq'::regclass);


--
-- Name: cliente id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente ALTER COLUMN id SET DEFAULT nextval('public.cliente_id_seq'::regclass);


--
-- Name: recurso id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recurso ALTER COLUMN id SET DEFAULT nextval('public.recurso_id_seq'::regclass);


--
-- Name: usuario id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario ALTER COLUMN id SET DEFAULT nextval('public.usuario_id_seq'::regclass);


--
-- Data for Name: agendamento; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.agendamento (id, id_cliente, id_recurso, data, hora, observacao) FROM stdin;
1	1	1	2026-10-20	14:00:00	Sess├úo de fechamento de bra├ºo em Realismo
2	2	3	2026-10-20	15:30:00	Aplica├º├úo de piercing no nariz
\.


--
-- Data for Name: cliente; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cliente (id, nome, documento, telefone) FROM stdin;
1	Carlos Silva	12345678900	11988887777
2	Mariana Souza	98765432100	11977776666
\.


--
-- Data for Name: recurso; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.recurso (id, nome, tipo) FROM stdin;
1	Maca 1 - Principal	Maca
2	Maca 2 - Secund├íria	Maca
3	Sala de Piercing	Sala
4	Tatuador Ricardo (Realismo)	Tatuador
5	Tatuadora Ana (Fineline)	Tatuadora
\.


--
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuario (id, nome, email, senha) FROM stdin;
1	Admin Est├║dio	admin@estudio.com	admin123
\.


--
-- Name: agendamento_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.agendamento_id_seq', 2, true);


--
-- Name: cliente_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cliente_id_seq', 2, true);


--
-- Name: recurso_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.recurso_id_seq', 5, true);


--
-- Name: usuario_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.usuario_id_seq', 1, true);


--
-- Name: agendamento agendamento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.agendamento
    ADD CONSTRAINT agendamento_pkey PRIMARY KEY (id);


--
-- Name: cliente cliente_documento_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_documento_key UNIQUE (documento);


--
-- Name: cliente cliente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (id);


--
-- Name: recurso recurso_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recurso
    ADD CONSTRAINT recurso_pkey PRIMARY KEY (id);


--
-- Name: usuario usuario_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_email_key UNIQUE (email);


--
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);


--
-- Name: agendamento agendamento_id_cliente_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.agendamento
    ADD CONSTRAINT agendamento_id_cliente_fkey FOREIGN KEY (id_cliente) REFERENCES public.cliente(id);


--
-- Name: agendamento agendamento_id_recurso_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.agendamento
    ADD CONSTRAINT agendamento_id_recurso_fkey FOREIGN KEY (id_recurso) REFERENCES public.recurso(id);


--
-- PostgreSQL database dump complete
--

\unrestrict PubeFEMudh8QSPTnI7GydJcU85B5CiP2DXxc22mAhI6OpoGFKE9wil6XMJVdcFG

