--
-- PostgreSQL database dump
--

-- Dumped from database version 13.4
-- Dumped by pg_dump version 13.4

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
-- Name: api; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.api (
    id_api bigint NOT NULL,
    created_at timestamp without time zone,
    created_by character varying(255),
    modified_at timestamp without time zone,
    modified_by character varying(255),
    description character varying(255),
    libelle character varying(255),
    managed_entity character varying(255),
    methode character varying(255),
    type_retour character varying(255),
    url character varying(255),
    action_id_type_action bigint,
    partenaire_id_partenaire bigint,
    reponse_id_type_reponse bigint,
    response_param_id_reponse_param bigint,
    response_param_id_api bigint
);


ALTER TABLE public.api OWNER TO postgres;

--
-- Name: api_id_api_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.api_id_api_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.api_id_api_seq OWNER TO postgres;

--
-- Name: api_id_api_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.api_id_api_seq OWNED BY public.api.id_api;


--
-- Name: app_client; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.app_client (
    id bigint NOT NULL,
    created_at timestamp without time zone,
    created_by character varying(255),
    modified_at timestamp without time zone,
    modified_by character varying(255),
    libelle character varying(255),
    nom character varying(255),
    nom_entreprise character varying(255),
    nom_representant character varying(255),
    numero_entreprise character varying(255),
    numero_representant character varying(255),
    user_id bigint,
    email_entreprise character varying(255)
);


ALTER TABLE public.app_client OWNER TO postgres;

--
-- Name: app_client_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.app_client_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.app_client_id_seq OWNER TO postgres;

--
-- Name: app_client_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.app_client_id_seq OWNED BY public.app_client.id;


--
-- Name: application; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.application (
    id bigint NOT NULL,
    created_at timestamp without time zone,
    created_by character varying(255),
    modified_at timestamp without time zone,
    modified_by character varying(255),
    cle_public character varying(255),
    description character varying(20000),
    logo character varying(255),
    nom character varying(255),
    user_id_user bigint
);


ALTER TABLE public.application OWNER TO postgres;

--
-- Name: application_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.application_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.application_id_seq OWNER TO postgres;

--
-- Name: application_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.application_id_seq OWNED BY public.application.id;


--
-- Name: evenement; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.evenement (
    id_evenement bigint NOT NULL,
    created_at timestamp without time zone,
    created_by character varying(255),
    modified_at timestamp without time zone,
    modified_by character varying(255),
    bouton character varying(255),
    description character varying(20000),
    libelle character varying(255) NOT NULL,
    lien character varying(255),
    photo_url character varying(255),
    user_id_user bigint
);


ALTER TABLE public.evenement OWNER TO postgres;

--
-- Name: evenement_id_evenement_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.evenement_id_evenement_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.evenement_id_evenement_seq OWNER TO postgres;

--
-- Name: evenement_id_evenement_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.evenement_id_evenement_seq OWNED BY public.evenement.id_evenement;


--
-- Name: membre; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.membre (
    id_membre bigint NOT NULL,
    created_at timestamp without time zone,
    created_by character varying(255),
    modified_at timestamp without time zone,
    modified_by character varying(255),
    adresse character varying(255),
    code integer,
    email character varying(255),
    nom character varying(255),
    numero_identite character varying(255),
    prenom character varying(255),
    statut boolean,
    telephone character varying(255)
);


ALTER TABLE public.membre OWNER TO postgres;

--
-- Name: membre_id_membre_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.membre_id_membre_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.membre_id_membre_seq OWNER TO postgres;

--
-- Name: membre_id_membre_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.membre_id_membre_seq OWNED BY public.membre.id_membre;


--
-- Name: membres_tontines; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.membres_tontines (
    id_tontine bigint NOT NULL,
    id_membre bigint NOT NULL
);


ALTER TABLE public.membres_tontines OWNER TO postgres;

--
-- Name: parametre; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.parametre (
    id_parametre bigint NOT NULL,
    created_at timestamp without time zone,
    created_by character varying(255),
    modified_at timestamp without time zone,
    modified_by character varying(255),
    key character varying(255),
    ordre integer,
    type character varying(255),
    value character varying(255),
    api_id_api bigint,
    has_children boolean,
    parent_id bigint,
    niveau integer,
    description character varying(255),
    code_type integer
);


ALTER TABLE public.parametre OWNER TO postgres;

--
-- Name: parametre_id_parametre_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.parametre_id_parametre_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.parametre_id_parametre_seq OWNER TO postgres;

--
-- Name: parametre_id_parametre_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.parametre_id_parametre_seq OWNED BY public.parametre.id_parametre;


--
-- Name: partenaire; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.partenaire (
    id_partenaire bigint NOT NULL,
    created_at timestamp without time zone,
    created_by character varying(255),
    modified_at timestamp without time zone,
    modified_by character varying(255),
    adresse_physique character varying(255),
    base_url character varying(255),
    email character varying(255) NOT NULL,
    login character varying(255),
    nif character varying(255),
    nom_compagnie character varying(255) NOT NULL,
    password character varying(255),
    photo_url character varying(255),
    rccm character varying(255),
    telephone character varying(255) NOT NULL,
    logo character varying(255)
);


ALTER TABLE public.partenaire OWNER TO postgres;

--
-- Name: partenaire_id_partenaire_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.partenaire_id_partenaire_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.partenaire_id_partenaire_seq OWNER TO postgres;

--
-- Name: partenaire_id_partenaire_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.partenaire_id_partenaire_seq OWNED BY public.partenaire.id_partenaire;


--
-- Name: possible_values; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.possible_values (
    id_possible_value bigint NOT NULL,
    message character varying(255),
    value character varying(255),
    response_param_id_reponse_param bigint
);


ALTER TABLE public.possible_values OWNER TO postgres;

--
-- Name: possible_values_id_possible_value_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.possible_values_id_possible_value_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.possible_values_id_possible_value_seq OWNER TO postgres;

--
-- Name: possible_values_id_possible_value_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.possible_values_id_possible_value_seq OWNED BY public.possible_values.id_possible_value;


--
-- Name: profile; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.profile (
    id_profile bigint NOT NULL,
    created_at timestamp without time zone,
    created_by character varying(255),
    modified_at timestamp without time zone,
    modified_by character varying(255),
    nom_profile character varying(255)
);


ALTER TABLE public.profile OWNER TO postgres;

--
-- Name: profile_id_profile_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.profile_id_profile_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.profile_id_profile_seq OWNER TO postgres;

--
-- Name: profile_id_profile_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.profile_id_profile_seq OWNED BY public.profile.id_profile;


--
-- Name: reponse_param; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.reponse_param (
    id_reponse_param bigint NOT NULL,
    key character varying(255),
    value character varying(255),
    id_api bigint
);


ALTER TABLE public.reponse_param OWNER TO postgres;

--
-- Name: reponse_param_id_reponse_param_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.reponse_param_id_reponse_param_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.reponse_param_id_reponse_param_seq OWNER TO postgres;

--
-- Name: reponse_param_id_reponse_param_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.reponse_param_id_reponse_param_seq OWNED BY public.reponse_param.id_reponse_param;


--
-- Name: session; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.session (
    id_session bigint NOT NULL,
    created_at timestamp without time zone,
    created_by character varying(255),
    modified_at timestamp without time zone,
    modified_by character varying(255),
    expire_date character varying(255),
    is_current boolean,
    token character varying(255),
    compagnie_id_partenaire bigint,
    partenaire_id_partenaire bigint,
    token_type character varying(255)
);


ALTER TABLE public.session OWNER TO postgres;

--
-- Name: session_id_session_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.session_id_session_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.session_id_session_seq OWNER TO postgres;

--
-- Name: session_id_session_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.session_id_session_seq OWNED BY public.session.id_session;


--
-- Name: session_tontine; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.session_tontine (
    id_session_tontine bigint NOT NULL,
    created_at timestamp without time zone,
    created_by character varying(255),
    modified_at timestamp without time zone,
    modified_by character varying(255),
    date_debut date,
    date_fin date,
    description character varying(20000),
    libelle character varying(255),
    montant double precision NOT NULL,
    owner_id_user bigint,
    tontine_id_tontine bigint,
    total_collecte double precision NOT NULL
);


ALTER TABLE public.session_tontine OWNER TO postgres;

--
-- Name: session_tontine_id_session_tontine_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.session_tontine_id_session_tontine_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.session_tontine_id_session_tontine_seq OWNER TO postgres;

--
-- Name: session_tontine_id_session_tontine_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.session_tontine_id_session_tontine_seq OWNED BY public.session_tontine.id_session_tontine;


--
-- Name: session_tontine_transaction; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.session_tontine_transaction (
    id_session_tontine_transaction bigint NOT NULL,
    created_at timestamp without time zone,
    created_by character varying(255),
    modified_at timestamp without time zone,
    modified_by character varying(255),
    code character varying(255),
    montant integer,
    statut character varying(255),
    evenement_id_evenement bigint,
    membre_id_membre bigint,
    partenaire_id_partenaire bigint,
    session_tontine_id_session_tontine bigint,
    transaction_id bigint
);


ALTER TABLE public.session_tontine_transaction OWNER TO postgres;

--
-- Name: session_tontine_transaction_id_session_tontine_transaction_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.session_tontine_transaction_id_session_tontine_transaction_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.session_tontine_transaction_id_session_tontine_transaction_seq OWNER TO postgres;

--
-- Name: session_tontine_transaction_id_session_tontine_transaction_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.session_tontine_transaction_id_session_tontine_transaction_seq OWNED BY public.session_tontine_transaction.id_session_tontine_transaction;


--
-- Name: tontine; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tontine (
    id_tontine bigint NOT NULL,
    created_at timestamp without time zone,
    created_by character varying(255),
    modified_at timestamp without time zone,
    modified_by character varying(255),
    bouton character varying(255),
    description character varying(20000),
    libelle character varying(255) NOT NULL,
    lien character varying(255),
    montant double precision NOT NULL,
    periodicite character varying(255),
    photo_url character varying(255),
    owner_id_user bigint
);


ALTER TABLE public.tontine OWNER TO postgres;

--
-- Name: tontine_id_tontine_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tontine_id_tontine_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tontine_id_tontine_seq OWNER TO postgres;

--
-- Name: tontine_id_tontine_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tontine_id_tontine_seq OWNED BY public.tontine.id_tontine;


--
-- Name: transaction; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.transaction (
    id_transaction bigint NOT NULL,
    code character varying(255),
    libelle_transaction character varying(255),
    montant integer,
    nom_client character varying(255),
    numero character varying(255) NOT NULL,
    prenom_client character varying(255),
    app_client_id bigint,
    evenement_id_evenement bigint,
    partenaire_id_partenaire bigint,
    type_transaction_id_type_transaction bigint,
    response_enquiry text,
    statut character varying(255),
    created_at timestamp without time zone,
    created_by character varying(255),
    modified_at timestamp without time zone,
    modified_by character varying(255)
);


ALTER TABLE public.transaction OWNER TO postgres;

--
-- Name: transaction_id_transaction_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.transaction_id_transaction_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.transaction_id_transaction_seq OWNER TO postgres;

--
-- Name: transaction_id_transaction_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.transaction_id_transaction_seq OWNED BY public.transaction.id_transaction;


--
-- Name: type_action; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.type_action (
    id_type_action bigint NOT NULL,
    libelle_type_action character varying(255)
);


ALTER TABLE public.type_action OWNER TO postgres;

--
-- Name: type_action_id_type_action_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.type_action_id_type_action_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.type_action_id_type_action_seq OWNER TO postgres;

--
-- Name: type_action_id_type_action_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.type_action_id_type_action_seq OWNED BY public.type_action.id_type_action;


--
-- Name: type_reponse; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.type_reponse (
    id_type_reponse bigint NOT NULL,
    libelle_type_reponse character varying(255),
    type integer
);


ALTER TABLE public.type_reponse OWNER TO postgres;

--
-- Name: type_reponse_id_type_reponse_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.type_reponse_id_type_reponse_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.type_reponse_id_type_reponse_seq OWNER TO postgres;

--
-- Name: type_reponse_id_type_reponse_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.type_reponse_id_type_reponse_seq OWNED BY public.type_reponse.id_type_reponse;


--
-- Name: type_transaction; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.type_transaction (
    id_type_transaction bigint NOT NULL,
    libelle_type_transaction character varying(255)
);


ALTER TABLE public.type_transaction OWNER TO postgres;

--
-- Name: type_transaction_id_type_transaction_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.type_transaction_id_type_transaction_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.type_transaction_id_type_transaction_seq OWNER TO postgres;

--
-- Name: type_transaction_id_type_transaction_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.type_transaction_id_type_transaction_seq OWNED BY public.type_transaction.id_type_transaction;


--
-- Name: utilisateur; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.utilisateur (
    id_user bigint NOT NULL,
    created_at timestamp without time zone,
    created_by character varying(255),
    modified_at timestamp without time zone,
    modified_by character varying(255),
    email character varying(255),
    nom character varying(255),
    password character varying(255),
    prenom character varying(255),
    username character varying(255),
    profile_id bigint,
    statut boolean,
    telephone character varying(255)
);


ALTER TABLE public.utilisateur OWNER TO postgres;

--
-- Name: utilisateur_id_user_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.utilisateur_id_user_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.utilisateur_id_user_seq OWNER TO postgres;

--
-- Name: utilisateur_id_user_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.utilisateur_id_user_seq OWNED BY public.utilisateur.id_user;


--
-- Name: api id_api; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.api ALTER COLUMN id_api SET DEFAULT nextval('public.api_id_api_seq'::regclass);


--
-- Name: app_client id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.app_client ALTER COLUMN id SET DEFAULT nextval('public.app_client_id_seq'::regclass);


--
-- Name: application id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.application ALTER COLUMN id SET DEFAULT nextval('public.application_id_seq'::regclass);


--
-- Name: evenement id_evenement; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.evenement ALTER COLUMN id_evenement SET DEFAULT nextval('public.evenement_id_evenement_seq'::regclass);


--
-- Name: membre id_membre; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.membre ALTER COLUMN id_membre SET DEFAULT nextval('public.membre_id_membre_seq'::regclass);


--
-- Name: parametre id_parametre; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.parametre ALTER COLUMN id_parametre SET DEFAULT nextval('public.parametre_id_parametre_seq'::regclass);


--
-- Name: partenaire id_partenaire; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.partenaire ALTER COLUMN id_partenaire SET DEFAULT nextval('public.partenaire_id_partenaire_seq'::regclass);


--
-- Name: possible_values id_possible_value; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.possible_values ALTER COLUMN id_possible_value SET DEFAULT nextval('public.possible_values_id_possible_value_seq'::regclass);


--
-- Name: profile id_profile; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profile ALTER COLUMN id_profile SET DEFAULT nextval('public.profile_id_profile_seq'::regclass);


--
-- Name: reponse_param id_reponse_param; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reponse_param ALTER COLUMN id_reponse_param SET DEFAULT nextval('public.reponse_param_id_reponse_param_seq'::regclass);


--
-- Name: session id_session; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.session ALTER COLUMN id_session SET DEFAULT nextval('public.session_id_session_seq'::regclass);


--
-- Name: session_tontine id_session_tontine; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.session_tontine ALTER COLUMN id_session_tontine SET DEFAULT nextval('public.session_tontine_id_session_tontine_seq'::regclass);


--
-- Name: session_tontine_transaction id_session_tontine_transaction; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.session_tontine_transaction ALTER COLUMN id_session_tontine_transaction SET DEFAULT nextval('public.session_tontine_transaction_id_session_tontine_transaction_seq'::regclass);


--
-- Name: tontine id_tontine; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tontine ALTER COLUMN id_tontine SET DEFAULT nextval('public.tontine_id_tontine_seq'::regclass);


--
-- Name: transaction id_transaction; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transaction ALTER COLUMN id_transaction SET DEFAULT nextval('public.transaction_id_transaction_seq'::regclass);


--
-- Name: type_action id_type_action; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.type_action ALTER COLUMN id_type_action SET DEFAULT nextval('public.type_action_id_type_action_seq'::regclass);


--
-- Name: type_reponse id_type_reponse; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.type_reponse ALTER COLUMN id_type_reponse SET DEFAULT nextval('public.type_reponse_id_type_reponse_seq'::regclass);


--
-- Name: type_transaction id_type_transaction; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.type_transaction ALTER COLUMN id_type_transaction SET DEFAULT nextval('public.type_transaction_id_type_transaction_seq'::regclass);


--
-- Name: utilisateur id_user; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utilisateur ALTER COLUMN id_user SET DEFAULT nextval('public.utilisateur_id_user_seq'::regclass);


--
-- Data for Name: api; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.api (id_api, created_at, created_by, modified_at, modified_by, description, libelle, managed_entity, methode, type_retour, url, action_id_type_action, partenaire_id_partenaire, reponse_id_type_reponse, response_param_id_reponse_param, response_param_id_api) FROM stdin;
2	2022-08-31 15:39:38.719	admin	2022-08-31 15:39:38.719	admin	description	libelle	PAIEMENT	GET	api	/get	1	2	\N	\N	\N
4	2022-09-01 10:38:51.88	admin	2022-09-01 13:08:10.799	admin	Récupérer le token d'authentification	Authentification	AUTHENTIFICATION	POST	Object	auth/oauth2/token	3	3	1	\N	\N
5	2022-09-02 15:13:54.45	admin	2022-09-02 15:13:54.45	admin	reponse de payement	response	PAIEMENT	GET	object	standard/v1/payments	1	3	1	\N	\N
3	2022-08-31 18:00:50.072	admin	2022-08-31 18:00:50.072	admin	Payer les frais	Paiement	PAIEMENT	POST	objet	merchant/v1/payments/	3	3	\N	5	\N
6	2022-09-16 12:46:51.643	admin	2022-09-16 12:46:51.643	admin	RETOURNER LE STATUT D'UNE  TXN	TXN_STATUS	PAIEMENT	GET	object	standard/v1/payments	2	3	1	\N	\N
\.


--
-- Data for Name: app_client; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.app_client (id, created_at, created_by, modified_at, modified_by, libelle, nom, nom_entreprise, nom_representant, numero_entreprise, numero_representant, user_id, email_entreprise) FROM stdin;
2	2022-08-31 10:49:06.497	admin	2022-08-31 10:50:39.309	admin	\N	nom Client	nomE	nomR	numE	numR	3	emailE
3	2022-08-31 10:51:19.303	admin	2022-08-31 10:51:19.303	admin	\N	nn	rt	rtn	rt	rtns	4	rt
5	2022-09-02 14:30:03.075	admin	2022-09-02 14:30:03.075	admin	\N	BeTicket	BeIt	Bako	98793379	98793379	6	beit@gmail.com
\.


--
-- Data for Name: application; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.application (id, created_at, created_by, modified_at, modified_by, cle_public, description, logo, nom, user_id_user) FROM stdin;
1	2022-12-30 17:02:23.236	admin	2022-12-30 17:02:23.236	admin	\N	Application de gestion des ateliers	e2122d19-6500-4aff-ab3c-e64c8e6e3dc6-undraw_creative_draft_vb5x.png	BeTailor	1
2	2022-12-30 17:13:39.524	admin	2022-12-30 17:13:39.524	admin	\N	Sawki est une plateforme de vente en ligne	581a924d-3263-46bc-9b43-f2587d15584a-undraw_Real_time_sync_re_nky7.png	Sawki	1
3	2022-12-30 17:31:25.057	admin	2022-12-30 17:31:25.057	admin	\N	Application de gestion de stock	9cb82e99-7f33-4037-b7bb-2f9a6f55c2ff-undraw_Web_developer_re_h7ie.png	BeStock	1
4	2023-01-07 12:11:50.357	admin	2023-01-07 12:11:50.357	admin	\N	Application de gestion médicale	373379fb-c531-4038-b061-e1de20100f6e-undraw_Thought_process_re_om58.png	BeMedical	1
5	2023-01-13 09:44:03.882	Abou	2023-01-13 09:44:03.882	Abou	\N	Plateforme destinée au commerce électronique	2e153de4-6df5-49f7-b755-ffdc56eda56d-logo_new.png	SAWKI	8
\.


--
-- Data for Name: evenement; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.evenement (id_evenement, created_at, created_by, modified_at, modified_by, bouton, description, libelle, lien, photo_url, user_id_user) FROM stdin;
1	2022-12-09 17:05:13.772	admin	2022-12-09 17:05:13.772	admin	Faire un dont	Dont aux ophélins test	Dont aux orphélins		aa8c962a-3954-4987-8959-586584db78de-undraw_Co-working_re_w93t.png	1
2	2022-12-10 14:14:36.908	admin	2022-12-10 14:14:36.908	admin	Cotiser	Cotisation decembre	Cotisation mensuelle		6dfae2d8-3374-45c5-ab97-003702aa0d3c-undraw_creative_draft_vb5x.png	1
3	2022-12-24 22:03:05.643	admin	2022-12-24 22:03:05.643	admin	Achter ticket	GDGDG\nDHBHDBHDù\nDBhbd	Concert Alradik		8525254c-ba5d-4771-a4d0-ea0bca2cbe25-undraw_Team_spirit_re_yl1v.png	\N
4	2022-12-27 00:06:16.063	Issamouss	2022-12-27 00:06:16.063	Issamouss	Faire un dont	Campage de dont aux orphélins	Don aux orphélins		c0bb4db4-17f3-4cde-9f8c-811062850c16-undraw_Developer_activity_re_39tg.png	7
5	2022-12-27 00:18:41.75	Issamouss	2022-12-27 00:18:41.75	Issamouss	Cotiser	Mata masu dubara	Asusu		d77d1224-585f-40d4-8a8e-61252c1b7ac7-undraw_Co-working_re_w93t.png	7
6	2023-01-13 00:03:45.677	Abou	2023-01-13 00:03:45.677	Abou	Faire un don	C'est une activité caritative qui consiste à venir en aide aide aux ophélins	Collect pour ophélins		a762cc07-8486-406c-8e25-74abbf347cb1-undraw_Our_solution_re_8yk6.png	8
7	2023-01-13 00:48:47.596	admin	2023-01-13 00:48:47.596	admin	Faire un don	Description	Collecte de fonds pour les ophelins		e81542e3-6709-43a8-a469-9d3922c2ad02-undraw_Bus_stop_re_h8ej.png	1
8	2023-01-13 09:22:37.203	Abou	2023-01-13 09:22:37.203	Abou	Faire un don	Description	Collecte Agagi Karkara		b214dabf-3e61-4bf6-9089-0625e1758c71-undraw_Developer_activity_re_39tg.png	8
\.


--
-- Data for Name: membre; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.membre (id_membre, created_at, created_by, modified_at, modified_by, adresse, code, email, nom, numero_identite, prenom, statut, telephone) FROM stdin;
4	2023-01-11 21:22:42.67	admin	2023-01-11 21:22:42.67	admin	Koubia 3	2465	issa.moussa@gmail.com	Issa	17/AN/1002	Moussa	f	90010203
5	2023-01-11 21:31:36.119	admin	2023-01-11 21:31:36.119	admin	YANTALA GANDA	1668	abou@beitniger.com	Abou	20/DRPN/A001	Sayabou	f	90107074
6	2023-01-13 09:25:00.828	Abou	2023-01-13 09:25:00.828	Abou	Magaria	1962	moutari.sanoussi@gmai.com	Moutari	20/DRPN/2002	Sanoussi	f	98601628
7	2023-01-13 09:25:54.139	Abou	2023-01-13 09:25:54.139	Abou	Arlit	5505	saley.farid@gmai.com	Saley	20/DRPN/ART/1002	Farida	f	90292093
\.


--
-- Data for Name: membres_tontines; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.membres_tontines (id_tontine, id_membre) FROM stdin;
1	4
1	5
3	6
3	7
\.


--
-- Data for Name: parametre; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.parametre (id_parametre, created_at, created_by, modified_at, modified_by, key, ordre, type, value, api_id_api, has_children, parent_id, niveau, description, code_type) FROM stdin;
29	2022-09-01 10:38:51.908	admin	2022-09-01 10:38:51.908	admin	Content-Type	1	Header	application/json	4	f	\N	0	\N	1
30	2022-09-01 10:38:51.917	admin	2022-09-01 10:38:51.917	admin	Accept	2	Header	/	4	f	\N	0	\N	1
12	2022-08-31 18:00:50.108	admin	2022-09-02 12:35:23.253	admin	Content-Type	0	Header	application/json	3	f	\N	0	\N	1
2	2022-08-31 15:39:38.994	admin	2022-09-05 12:57:35.704	admin	id	1	RequestBody	5	2	t	\N	0	\N	0
77	2022-09-05 12:57:34.799	admin	2022-09-05 13:02:45.634	admin	test2	0	RequestBody	\N	2	t	\N	0	\N	0
8	2022-08-31 17:18:09.248	admin	2022-09-07 17:06:52.742	admin	currency	2	RequestBody	\N	2	f	22	0	\N	0
33	2022-09-01 10:38:51.933	admin	2022-09-01 10:38:51.933	admin	grant_type	3	RequestBody	client_credentials	4	f	\N	0	\N	0
62	2022-09-02 15:13:54.52	admin	2022-09-02 17:28:22.791	admin	message	0	Response		5	f	\N	3	\N	2
34	2022-09-01 16:47:42.508	admin	2022-09-01 16:47:42.508	admin	access_token	1	Response	\N	4	f	\N	0	\N	2
35	2022-09-01 16:48:06.388	admin	2022-09-01 16:48:06.388	admin	expires_in	2	Response	\N	4	f	\N	0	\N	2
36	2022-09-01 16:48:36.572	admin	2022-09-01 16:48:36.572	admin	token_type	3	Response	\N	4	f	\N	0	\N	2
44	2022-09-01 17:37:06.465	admin	2022-09-09 11:48:16.354	admin	status	0	Response	\N	3	t	\N	1	\N	2
45	2022-09-01 17:37:34.479	admin	2022-09-09 11:49:37.727	admin	code	0	Response	\N	3	f	44	2	\N	2
47	2022-09-01 17:38:34.725	admin	2022-09-09 11:50:04.421	admin	result_code	0	Response	\N	3	f	44	2	\N	2
48	2022-09-01 17:38:58.31	admin	2022-09-09 11:50:18.534	admin	response_code	1	Response	\N	3	f	44	2	\N	2
49	2022-09-01 17:39:26.986	admin	2022-09-09 11:50:33.106	admin	success	0	Response	\N	3	f	44	2	\N	2
22	2022-08-31 18:07:29.306	admin	2022-09-07 17:06:53.737	admin	cle	0	\N	\N	2	t	\N	0	\N	4
10	2022-08-31 17:23:11.94	admin	2022-09-07 17:06:12.972	admin	test	0	\N	\N	2	f	11	0	\N	4
11	2022-08-31 17:23:43.516	admin	2022-09-07 17:06:13.976	admin	test1	0	\N	\N	2	t	\N	0	\N	4
82	2022-09-07 15:54:49.703	admin	2022-09-07 15:54:49.703	admin	\N	0	\N	\N	2	f	\N	0	\N	4
4	2022-08-31 15:40:39.259	admin	2022-08-31 17:23:44.378	admin	num	2	PathVariable	87	2	t	\N	0	\N	4
5	2022-08-31 17:03:44.692	admin	2022-08-31 17:23:12.919	admin	transaction	0	\N	\N	2	t	\N	0	\N	4
21	2022-08-31 18:04:49.586	admin	2022-09-05 13:06:28.603	admin	test2	0	\N	\N	2	t	\N	0	\N	4
78	2022-09-05 13:01:38.476	admin	2022-09-05 13:02:44.838	admin	testtttttttttt	0	\N	testtttttttt	2	f	\N	0	\N	4
14	2022-08-31 18:00:50.125	admin	2022-09-02 12:35:36.697	admin	X-Country	0	Header	NE	3	f	\N	0	\N	1
55	2022-09-02 15:13:54.484	admin	2022-09-02 15:13:54.484	admin	X-Currency	0	Header	XOF	5	f	\N	0	\N	1
6	2022-08-31 17:15:24.35	admin	2022-08-31 18:04:50.128	admin	amount	1	RequestBody	\N	\N	t	\N	0	\N	0
26	2022-08-31 18:10:06.972	admin	2022-09-05 14:53:28.882	admin	country	0	RequestBody	NE	3	f	19	2	\N	0
43	2022-09-01 17:36:40.755	admin	2022-09-09 11:50:49.95	admin	data	0	Response	\N	3	t	\N	1	\N	2
51	2022-09-01 17:40:22.425	admin	2022-09-09 11:51:03.06	admin	id	0	Response	\N	3	f	50	3	\N	2
52	2022-09-01 17:40:41.247	admin	2022-09-09 11:51:15.281	admin	status	0	Response	\N	3	f	50	3	\N	2
81	2022-09-05 15:56:44.217	admin	2022-09-06 11:38:27.836	admin	message	0	Response	\N	3	f	44	2	\N	2
83	2022-09-07 16:34:47.332	admin	2022-09-07 16:34:47.332	admin	test5	0	Response	t5	2	f	80	3	\N	2
65	2022-09-02 15:13:54.533	admin	2022-09-02 17:29:02.449	admin	message	0	Response		5	f	\N	2	\N	2
57	2022-09-02 15:13:54.496	admin	2022-09-02 17:27:00.359	admin	data	0	Response		5	f	\N	1	\N	2
58	2022-09-02 15:13:54.502	admin	2022-09-02 17:27:17.754	admin	status	0	Response		5	f	\N	1	\N	2
59	2022-09-02 15:13:54.506	admin	2022-09-02 17:27:29.012	admin	transaction	0	Response		5	f	\N	2	\N	2
15	2022-08-31 18:00:50.134	admin	2022-09-02 12:35:45.773	admin	X-Currency	0	Header	XOF	3	f	\N	0	\N	1
53	2022-09-02 15:13:54.469	admin	2022-09-02 15:13:54.469	admin	Accept	0	Header	/	5	f	\N	0	\N	1
54	2022-09-02 15:13:54.48	admin	2022-09-02 15:13:54.48	admin	X-Country	0	Header	NE	5	f	\N	0	\N	1
20	2022-08-31 18:02:29.224	admin	2022-09-05 14:51:26.48	admin	country	0	RequestBody	NE	3	f	18	2	\N	0
23	2022-08-31 18:08:41.877	admin	2022-09-05 14:52:59.142	admin	currency	0	RequestBody	XOF	3	f	18	2	\N	0
27	2022-08-31 18:10:37.307	admin	2022-09-05 14:53:57.98	admin	currency	0	RequestBody	XOF	3	f	19	2	\N	0
19	2022-08-31 18:00:50.154	admin	2022-09-05 15:11:19.434	admin	transaction	0	RequestBody		3	t	\N	1	\N	0
18	2022-08-31 18:00:50.148	admin	2022-09-05 15:11:59.698	admin	subscriber	0	RequestBody		3	t	\N	1	\N	0
60	2022-09-02 15:13:54.514	admin	2022-09-02 17:27:40.4	admin	airtel_money_id	0	Response		5	f	\N	3	\N	2
61	2022-09-02 15:13:54.517	admin	2022-09-02 17:27:52.007	admin	id	0	Response		5	f	\N	3	\N	2
66	2022-09-02 15:13:54.538	admin	2022-09-02 17:29:24.995	admin	result_code	0	Response		5	f	\N	2	\N	2
68	2022-09-02 15:13:54.548	admin	2022-09-02 17:29:39.318	admin	success	0	Response		5	f	\N	2	\N	2
50	2022-09-01 17:40:01.075	admin	2022-09-09 11:51:03.781	admin	transaction	0	Response	\N	3	t	43	2	\N	2
64	2022-09-02 15:13:54.531	admin	2022-09-02 17:28:48.574	admin	code	0	Response		5	f	\N	2	\N	2
13	2022-08-31 18:00:50.121	admin	2022-09-15 17:37:44.974	admin	Accept	0	Header	*/*	3	f	\N	0	\N	1
63	2022-09-02 15:13:54.528	admin	2023-01-08 12:44:11.058	admin	status	0	Response		5	t	\N	3	\N	2
67	2022-09-02 15:13:54.545	admin	2023-01-08 12:44:08.638	admin	response_code	1	Response		5	f	63	2	\N	2
56	2022-09-02 15:13:54.488	admin	2023-01-20 18:07:09.75	anonymousUser	Authorization	1	Header	bearer OfWx8fwXaCh6ooWho9haqAOKqKD65Vv8	5	f	\N	0	\N	1
24	2022-08-31 18:09:12.264	admin	2022-10-15 13:01:07.616	admin	msisdn	2	RequestBody	98793379	3	f	18	2	\N	0
31	2022-09-01 10:38:51.922	admin	2022-09-19 13:04:07.173	admin	client_id	1	RequestBody	7b9e0597-7ab2-4e52-9b78-a7cb0a7d843f	4	f	\N	0	\N	0
25	2022-08-31 18:09:38.149	admin	2024-09-16 12:15:55.512	admin	amount	3	RequestBody	200	3	f	19	2	\N	0
28	2022-08-31 18:11:09.152	admin	2024-09-16 12:15:55.512	admin	id	4	RequestBody	44	3	f	19	2	\N	0
93	2022-09-16 12:57:10.081	admin	2023-01-08 00:54:04.398	admin	message	0	Response	success	6	f	91	3	Le message de la transaction	0
85	2022-09-16 12:46:51.71	admin	2022-09-16 12:46:51.71	admin	id	1	PathVariable		6	f	\N	0	\N	0
86	2022-09-16 12:46:51.715	admin	2022-09-16 12:46:51.715	admin	Accept	4	Header	*/*	6	f	\N	0	\N	0
87	2022-09-16 12:46:51.718	admin	2022-09-16 12:46:51.718	admin	X-Country	2	Header	NE	6	f	\N	0	\N	0
88	2022-09-16 12:46:51.722	admin	2022-09-16 12:46:51.722	admin	X-Currency	3	Header	XOF	6	f	\N	0	\N	0
89	2022-09-16 12:46:51.726	admin	2022-09-16 12:46:51.726	admin	Authorization	1	Header		6	f	\N	0	\N	0
90	2022-09-16 12:52:48.911	admin	2022-09-16 12:54:25.93	admin	data	0	Response	\N	6	t	\N	1	Data de la Txn	0
92	2022-09-16 12:55:41.689	admin	2022-09-16 12:55:41.689	admin	airtel_money_id	0	Response	\N	6	f	91	3	Correspond à l'ID de la Txn	0
91	2022-09-16 12:54:21.396	admin	2022-09-16 12:55:44.022	admin	transaction	0	Response	\N	6	t	90	2	Txn	0
94	2022-09-16 12:58:07.392	admin	2022-09-16 12:58:07.392	admin	status	0	Response	SUCCESS	6	f	91	3	Le status de la transaction	0
96	2022-09-16 13:00:16.39	admin	2022-09-16 13:00:16.39	admin	response_code	0	Response	\N	6	f	95	2	\N	0
95	2022-09-16 12:59:25.155	admin	2022-09-16 13:00:19.155	admin	status	0	Response	\N	6	t	\N	1	STATUS DE TXN	0
32	2022-09-01 10:38:51.93	admin	2022-09-19 13:44:57.065	admin	client_secret	2	RequestBody	753c8081-a2d8-4192-b97e-f0dcf26417a1	4	f	\N	0	\N	0
17	2022-08-31 18:00:50.141	admin	2024-09-16 12:15:55.507	admin	reference	1	RequestBody	Cotisation	3	f	\N	1	\N	0
16	2022-08-31 18:00:50.137	admin	2024-09-16 12:16:05.594	admin	Authorization	1	Header	bearer jNS3ttwaIvuvdS2K7XA3NpIgV6l8QCyT	3	f	\N	0	\N	1
84	2022-09-15 10:52:20.897	admin	2022-12-25 11:07:09.031	admin	\N	0	\N	\N	3	t	\N	0	\N	0
\.


--
-- Data for Name: partenaire; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.partenaire (id_partenaire, created_at, created_by, modified_at, modified_by, adresse_physique, base_url, email, login, nif, nom_compagnie, password, photo_url, rccm, telephone, logo) FROM stdin;
2	2022-08-30 15:57:40.6	admin	2022-08-30 15:57:40.6	admin	addresse	baseUrl	ppp@gmail.com	login	nif	Partenaire1	password	\N	rccm	11111111	\N
5	2022-12-13 23:37:06.382	admin	2022-12-17 13:46:03.404	admin		money.moov.africa/openapi	moov@moov.ne			Moov Money		\N		94004003	9d349c7a-bd7c-4317-97ad-82bc9a9caf32-Flooz-benin-vers-togo.png
3	2022-08-31 17:45:13.364	admin	2022-12-17 19:34:43.014	admin	Zone Industrielle de Niamey	https://openapi.airtel.africa/	contact@airtel.ne	7b9e0597-7ab2-4e52-9b78-a7cb0a7d843f		Airtel Money	753c8081-a2d8-4192-b97e-f0dcf26417a1	\N		97777203	f1ee54cd-a296-42cd-9fc9-67321326ca60-logo_airtel_money.png
\.


--
-- Data for Name: possible_values; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.possible_values (id_possible_value, message, value, response_param_id_reponse_param) FROM stdin;
3	echec	failed	1
5	Code invalide	DP00800001002	5
6	Solde insuffisant	DP00800001003	5
7	montant doit être supérieur à 100fr	DP00800001004	5
4	Transaction effectuée avec succès	DP00800001006	5
12	Vous n'avez pas saisie votre code airtel money	DP00800001005	5
2	Non valide	Non Ok	4
13	SUCCESS	DP00800001001	10
14	Id Incorrect	DP00800001005	10
17	Succès	SUCCESS	11
18	ECHEC	FAILED	11
19	Succès	SUCCESS	11
20	ECHEC	FAILED	11
21	Transaction effectuée avec succès !	DP00800001001	12
22	Code saisi incorrect	DP00800001002	12
23	Vous ne disposez pas assez de fond pour effectuer cette opération	DP00800001003	12
24	L'ID de la transaction est incorrecte	DP00800001005	12
25	Votre transaction est en attente de validation	DP00800001006	12
26	Transaction effectuée avec succès !	DP00800001001	12
27	Code saisi incorrect	DP00800001002	12
28	Vous ne disposez pas assez de fond pour effectuer cette opération	DP00800001003	12
29	L'ID de la transaction est incorrecte	DP00800001005	12
30	Votre transaction est en attente de validation	DP00800001006	12
\.


--
-- Data for Name: profile; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.profile (id_profile, created_at, created_by, modified_at, modified_by, nom_profile) FROM stdin;
1	\N	\N	\N	\N	admin
2	2022-08-30 17:52:08.779	admin	2022-08-30 17:52:08.779	admin	User
3	2022-12-26 23:42:07.67	admin	2022-12-26 23:42:07.67	admin	Client
\.


--
-- Data for Name: reponse_param; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.reponse_param (id_reponse_param, key, value, id_api) FROM stdin;
10	response_code	DP00800001001	6
1	status	success	2
4	Sucess	Ok	2
5	response_code	DP00800001006	3
11	STATUS	SUCCESS	2
12	response_code	DP00800001006	5
\.


--
-- Data for Name: session; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.session (id_session, created_at, created_by, modified_at, modified_by, expire_date, is_current, token, compagnie_id_partenaire, partenaire_id_partenaire, token_type) FROM stdin;
1	2022-09-01 16:57:16.956	admin	2022-09-01 17:00:02.363	admin	2022-09-01T17:00:16.899095300	f	QqS9U7PYsKtCiWscVpGpW6GRMFebsKuS	\N	3	\N
2	2022-09-01 17:00:02.331	admin	2022-09-01 17:59:01.193	admin	2022-09-01T17:03:02.272095500+0000	f	clMEPsM7dyi8DrEuU9BR7CWg8tsDFGAi	\N	3	\N
3	2022-09-01 17:59:01.139	admin	2022-09-02 11:27:12.558	admin	2022-09-01T18:02:01.060656800+0000	f	rBQtwNpFLDIYBexlAqTAw0CTei7Rh5V4	\N	3	bearer
4	2022-09-02 11:27:12.532	admin	2022-09-02 11:31:47.222	admin	2022-09-02T11:30:12.476912500+0000	f	dQEd7es1uuHQfTInDcMlVRD1D9qXMCRK	\N	3	bearer
5	2022-09-02 11:31:47.203	admin	2022-09-02 11:41:56.69	admin	2022-09-02T11:34:47.136622100+0000	f	6tIiNe69gdxi6c7zSijgUiVq2micQBwj	\N	3	bearer
6	2022-09-02 11:41:56.665	admin	2022-09-02 11:46:02.755	admin	2022-09-02T11:44:56.612361700+0000	f	ifIxYBtZvy2bU2wiAeTm6VxuycvIIZcK	\N	3	bearer
7	2022-09-02 11:46:02.731	admin	2022-09-02 11:50:34.388	admin	2022-09-02T11:49:02.675461200+0000	f	QNLSoXEhlZjnCVtpznHZhB1LRAL272Y0	\N	3	bearer
8	2022-09-02 11:50:34.369	admin	2022-09-02 12:08:52.253	admin	2022-09-02T11:53:34.298402400+0000	f	kWCIt9Y7LbAejdsK34r0IalI3wMMe7iU	\N	3	bearer
9	2022-09-02 12:08:52.232	admin	2022-09-02 12:28:43.017	admin	2022-09-02T12:11:52.172134500+0000	f	5RhrEjMxWh02WCFHHptt0XOQeBx0CCRz	\N	3	bearer
10	2022-09-02 12:28:42.998	admin	2022-09-02 12:41:35.687	admin	2022-09-02T12:31:42.938976+0000	f	047xsYs0M9BEvvK68iDIftiieAAoyL2o	\N	3	bearer
11	2022-09-02 12:41:35.673	admin	2022-09-02 13:04:05.201	admin	2022-09-02T12:44:35.645610+0000	f	zAN2ijOpn2B1Fn8YMqyGnRBzHY94QlTq	\N	3	bearer
12	2022-09-02 13:04:05.195	admin	2022-09-02 14:49:18.995	admin	2022-09-02T13:07:05.168419200+0000	f	IZVPGP7RIMQ6faE1E0Y4ybv5hz2aQMlg	\N	3	bearer
13	2022-09-02 14:49:18.987	admin	2022-09-02 14:56:04.452	admin	2022-09-02T14:52:18.938822900+0000	f	Oi8yosRbGD2ae1zASSIFLEgl7TQtVw0l	\N	3	bearer
14	2022-09-02 14:56:04.447	admin	2022-09-05 15:22:28.955	admin	2022-09-02T14:59:04.441718400+0000	f	4kkVO7OeExgstEUECL04B0ZGX4P5VcNu	\N	3	bearer
15	2022-09-05 15:22:28.95	admin	2022-09-05 15:28:59.259	admin	2022-09-05T15:25:28.929578+0000	f	rowU7IXGXaM6MFNmqFxEHJ5nZ6T71jGj	\N	3	bearer
16	2022-09-05 15:28:59.25	admin	2022-09-05 15:32:24.4	admin	2022-09-05T15:31:59.190582600+0000	f	HWIDJmlOBSw24pgMVPADB8cGFg9LEuZt	\N	3	bearer
17	2022-09-05 15:32:24.381	admin	2022-09-05 15:38:05.99	admin	2022-09-05T15:35:24.315980300+0000	f	3y6qsYdZczHPDgjFYagggBsF6zYXnJIT	\N	3	bearer
18	2022-09-05 15:38:05.964	admin	2022-09-05 15:42:13.054	admin	2022-09-05T15:41:05.900415500+0000	f	TKXFH9XMA07x8FeGsbbHr8U99IhMYuAr	\N	3	bearer
19	2022-09-05 15:42:13.041	admin	2022-09-05 15:45:57.767	admin	2022-09-05T15:45:13.020334700+0000	f	RohWW0QWHAtCowheZHgvh2U02JrAzwaG	\N	3	bearer
20	2022-09-05 15:45:57.746	admin	2022-09-05 15:50:07.483	admin	2022-09-05T15:48:57.702955200+0000	f	TuS4pkRN0jnRfLCjIWuqMFJlYJsdbzSX	\N	3	bearer
21	2022-09-05 15:50:07.461	admin	2022-09-05 17:19:20.825	admin	2022-09-05T15:53:07.395655+0000	f	VpIJRl2Sad1W51l2qyOdYNoSejxFQq91	\N	3	bearer
22	2022-09-05 17:19:20.804	admin	2022-09-05 17:23:19.617	admin	2022-09-05T17:22:20.738559400+0000	f	cKyM5mn3qlkhe96dKTHugGNio1m64K3v	\N	3	bearer
23	2022-09-05 17:23:19.598	admin	2022-09-05 17:51:50.625	admin	2022-09-05T17:26:19.538366500+0000	f	32p0NDlnAT8Zi6TIxVh1DDxvgRSQOJjq	\N	3	bearer
24	2022-09-05 17:51:50.588	admin	2022-09-05 17:57:03.95	admin	2022-09-05T17:54:50.540906300+0000	f	2d8vVMfV6zdF4kJlFbZQpl00T0VUubvp	\N	3	bearer
25	2022-09-05 17:57:03.915	admin	2022-09-05 18:06:28.075	admin	2022-09-05T18:00:03.858107800+0000	f	XUusE5GdxScvayEdmlj4tg6KxXEm4ndT	\N	3	bearer
26	2022-09-05 18:06:28.06	admin	2022-09-09 11:36:45.537	admin	2022-09-05T18:09:28.031849600+0000	f	vqYITxDT5z6KZKcCuh5RFKG6WHOrBzH6	\N	3	bearer
27	2022-09-09 11:36:45.531	admin	2022-09-09 11:51:40.185	admin	2022-09-09T11:39:45.505760600+0000	f	gJJRdlmUAwZBjOVP28kmPevWpz4DMfe1	\N	3	bearer
28	2022-09-09 11:51:40.18	admin	2022-09-09 11:59:10.992	admin	2022-09-09T11:54:40.155417800+0000	f	NAneMQVPkQE98xGTr9O99iH8c639rUrh	\N	3	bearer
29	2022-09-09 11:59:10.988	admin	2022-09-09 12:11:25.351	admin	2022-09-09T12:02:10.956118600+0000	f	aOZT0kg0R4nx7KEWYMQ0lL3eVhh24spQ	\N	3	bearer
30	2022-09-09 12:11:25.347	admin	2022-09-09 12:15:00.066	admin	2022-09-09T12:14:25.331821100+0000	f	qUtGqEPjJJcQxD9gBRan3YLQ5riMZGib	\N	3	bearer
31	2022-09-09 12:15:00.062	admin	2022-09-09 12:30:01.704	admin	2022-09-09T12:18:00.047412800+0000	f	SRFjUYEWCd0Qxc1jqyMD61NFgQTwEfWy	\N	3	bearer
32	2022-09-09 12:30:01.701	admin	2022-09-09 12:34:09.908	admin	2022-09-09T12:33:01.686898500+0000	f	hUS5BbN2cQXtYsFYFUaS0N6eCIFnz9Rt	\N	3	bearer
33	2022-09-09 12:34:09.901	admin	2022-09-09 12:40:26.548	admin	2022-09-09T12:37:09.874434600+0000	f	xAFr3lnPhCQPf5kEuFiqZ1eq1itfjB25	\N	3	bearer
34	2022-09-09 12:40:26.544	admin	2022-09-09 12:43:31.405	admin	2022-09-09T12:43:26.522196600+0000	f	MEBGGxzgIUtuF2RdwKyprj5doKOe2Nri	\N	3	bearer
35	2022-09-09 12:43:31.398	admin	2022-09-09 12:46:36.923	admin	2022-09-09T12:46:31.371926900+0000	f	h8b4knDVu2KYIrqnShzM5R36aTf4mauw	\N	3	bearer
36	2022-09-09 12:46:36.913	admin	2022-09-09 12:50:48.355	admin	2022-09-09T12:49:36.884574300+0000	f	kD6IIOYOzACPQ3UOd0QSLYx65VDFxaGS	\N	3	bearer
37	2022-09-09 12:50:48.346	admin	2022-09-09 12:54:22.878	admin	2022-09-09T12:53:48.315568200+0000	f	0wcdZP5g92Bjz1DQtwrlu4XFjvr8oIdq	\N	3	bearer
38	2022-09-09 12:54:22.872	admin	2022-09-09 13:03:39.223	admin	2022-09-09T12:57:22.841254500+0000	f	PPQKJlSmDwjEZYmqBTz5bav2yBAVLQuc	\N	3	bearer
39	2022-09-09 13:03:39.218	admin	2022-09-09 13:54:26.122	admin	2022-09-09T13:06:39.191833500+0000	f	IJ3AkmlyOrHLJDYKK2hH3i0x1ziZYyLK	\N	3	bearer
40	2022-09-09 13:54:26.116	admin	2022-09-09 13:58:09.418	admin	2022-09-09T13:57:26.090799300+0000	f	FfeKVrHfeeuJe5V2klb3PVfQdyjxJbLY	\N	3	bearer
41	2022-09-09 13:58:09.414	admin	2022-09-09 14:05:50.068	admin	2022-09-09T14:01:09.396448+0000	f	wSnSC7gUd97TWz2DwaggCjEPAkLCzLrR	\N	3	bearer
42	2022-09-09 14:05:50.062	admin	2022-09-09 15:21:47.907	admin	2022-09-09T14:08:50.039036800+0000	f	toXMXg9k08JQBmAj4i5IxVLNqf7BiuBG	\N	3	bearer
43	2022-09-09 15:21:47.9	admin	2022-09-09 15:26:53.258	admin	2022-09-09T15:24:47.874532900+0000	f	lWvgHQVYIHE613ruLpkDRhAPFFBGJca3	\N	3	bearer
44	2022-09-09 15:26:53.25	admin	2022-09-09 16:13:06.458	admin	2022-09-09T15:29:53.224763700+0000	f	BDmJqB4Ggdy29GH2TaPPEQ4CeX6QICm6	\N	3	bearer
45	2022-09-09 16:13:06.452	admin	2022-09-09 16:16:08.507	admin	2022-09-09T16:16:06.433057+0000	f	ZvLSaJH57gPATGxRd180WdnQzsuoPJQa	\N	3	bearer
46	2022-09-09 16:16:08.498	admin	2022-09-09 16:54:50.197	admin	2022-09-09T16:19:08.476545600+0000	f	ubge1XHR7aakKABGazMG7qE3TgcVWoWY	\N	3	bearer
47	2022-09-09 16:54:50.193	admin	2022-09-09 16:58:10.48	admin	2022-09-09T16:57:50.132197600+0000	f	jhoyJJ8RzRDbBSDVvQPPwdUOi8F682xP	\N	3	bearer
49	2022-09-09 17:14:54.69	admin	2022-09-09 17:23:49.209	admin	2022-09-09T17:17:54.662010500+0000	f	6FkD1PXiqFeHoBGhQz1LzQjMckoNkF2u	\N	3	bearer
48	2022-09-09 16:58:10.471	admin	2022-09-09 17:14:54.697	admin	2022-09-09T17:01:10.448192300+0000	f	TtXjVhDi4z6sb6OK1NfFudOC2QqX6JU5	\N	3	bearer
50	2022-09-09 17:23:49.204	admin	2022-09-09 17:27:37.851	admin	2022-09-09T17:26:49.175219900+0000	f	GlWpvtiiGl404bGsziSSnfHmUxFmYtRi	\N	3	bearer
51	2022-09-09 17:27:37.841	admin	2022-09-09 18:00:53.195	admin	2022-09-09T17:30:37.810949+0000	f	75Ph7SvbfOLuEk0ICwHWmIjWj5yUHV2a	\N	3	bearer
52	2022-09-09 18:00:53.191	admin	2022-09-09 18:05:15.58	admin	2022-09-09T18:03:53.176053100+0000	f	83OveOW9y3rjkEaSPvc1VhjqE9yvvjSp	\N	3	bearer
53	2022-09-09 18:05:15.573	admin	2022-09-09 18:09:06.953	admin	2022-09-09T18:08:15.542339100+0000	f	gFcPN9vsy6XuK975529RpkGSnwn1w2lQ	\N	3	bearer
54	2022-09-09 18:09:06.947	admin	2022-09-09 18:19:00.694	admin	2022-09-09T18:12:06.927126800+0000	f	um0kgyHHhiDoA4Y9GLbCSRguwY0bn7zg	\N	3	bearer
55	2022-09-09 18:19:00.686	admin	2022-09-09 18:22:50.502	admin	2022-09-09T18:22:00.663671100+0000	f	bTRo173uFXOdJtN2OnhTdZ4YQ66Qh2r6	\N	3	bearer
56	2022-09-09 18:22:50.498	admin	2022-09-12 11:29:49.946	admin	2022-09-09T18:25:50.471857500+0000	f	K5lflJcvxYKW7tJbKRFmwtB5gNADrMpS	\N	3	bearer
57	2022-09-12 11:29:49.938	admin	2022-09-12 11:37:20.432	admin	2022-09-12T11:32:49.919423900+0000	f	yWiGnNLx4n5aUdGxZSaKUs17WBWpjVXe	\N	3	bearer
58	2022-09-12 11:37:20.426	admin	2022-09-12 11:52:28.271	admin	2022-09-12T11:40:20.418444200+0000	f	GczJrMrhcv2HlvF931VWyntNuS71s2OO	\N	3	bearer
59	2022-09-12 11:52:28.265	admin	2022-09-12 11:55:54.78	admin	2022-09-12T11:55:28.259891+0000	f	9ZvsKTcT9JAkoI2ycqRFBAbCC2hbPsXn	\N	3	bearer
60	2022-09-12 11:55:54.771	admin	2022-09-12 11:59:11.727	admin	2022-09-12T11:58:54.747018900+0000	f	3T1Xg6bOMJKjGwQ8stewp0HZ08DTlP8l	\N	3	bearer
61	2022-09-12 11:59:11.722	admin	2022-09-12 12:04:15.495	admin	2022-09-12T12:02:11.704724600+0000	f	16EgEIh215q7thNCJ5itsFYChIX5SPDm	\N	3	bearer
62	2022-09-12 12:04:15.49	admin	2022-09-12 12:07:23.487	admin	2022-09-12T12:07:15.466750200+0000	f	ALpY9EryU67jb95KEOjC5Kh6S34TWKl5	\N	3	bearer
63	2022-09-12 12:07:23.457	admin	2022-09-12 12:19:13.683	admin	2022-09-12T12:10:23.401370600+0000	f	MHQSitmnYDRHZSIXvKE2GhlHXCaAUTjQ	\N	3	bearer
64	2022-09-12 12:19:13.675	admin	2022-09-12 13:20:58.271	admin	2022-09-12T12:22:13.655633500+0000	f	MqIPtj3iVUrLNHj2TzsYv47rFEZ6ni6h	\N	3	bearer
65	2022-09-12 13:20:58.253	admin	2022-09-12 14:08:07.021	admin	2022-09-12T13:23:58.230338700+0000	f	TN44O3prgpFnO0pYisdy8DAtL5hP2kDJ	\N	3	bearer
66	2022-09-12 14:08:07.013	admin	2022-09-12 14:19:56.218	admin	2022-09-12T14:11:07.006678100+0000	f	x3BhBTuBO44Q4pAC5NcwQVIhwi4C4SPy	\N	3	bearer
67	2022-09-12 14:19:56.207	admin	2022-09-12 14:23:12.085	admin	2022-09-12T14:22:56.200131700+0000	f	S6Pg6zfsYV7LT6sKY8D8EFXNH6Mnz9Tv	\N	3	bearer
68	2022-09-12 14:23:12.082	admin	2022-09-12 14:37:19.235	admin	2022-09-12T14:26:12.080612800+0000	f	5d6c9WzDHgNckdHv5g4P8WftIf3p0CTZ	\N	3	bearer
69	2022-09-12 14:37:19.227	admin	2022-09-12 15:26:06.377	admin	2022-09-12T14:40:19.223801500+0000	f	1y9kOqhqnFqJIogs220lBhAGyhFsy5R8	\N	3	bearer
70	2022-09-12 15:26:06.367	admin	2022-09-12 15:31:03.656	admin	2022-09-12T15:29:06.359468600+0000	f	2uboeBLCBbStflnZKQHZBPQBW0Lcj3gg	\N	3	bearer
71	2022-09-12 15:31:03.651	admin	2022-09-12 15:37:33.586	admin	2022-09-12T15:34:03.607250600+0000	f	ICcott364IWwUEbT5pkXGqPLQI8Gs0f5	\N	3	bearer
72	2022-09-12 15:37:33.581	admin	2022-09-12 16:24:27.725	admin	2022-09-12T15:40:33.535489+0000	f	FN4XCv3WiHfq3YHZnIVFOH7nGrDd2N4c	\N	3	bearer
73	2022-09-12 16:24:27.717	admin	2022-09-15 11:08:34.357	admin	2022-09-12T16:27:27.694061800+0000	f	8LFPRBvxLNXg2BFPooD9Zov33ESpSLJi	\N	3	bearer
74	2022-09-15 11:08:34.35	admin	2022-09-15 11:34:48.157	admin	2022-09-15T11:11:34.313120400+0000	f	URMurAVW1zZ9Uk2TiB90qICr8tvbRbdu	\N	3	bearer
75	2022-09-15 11:34:48.157	admin	2022-09-15 14:57:59.259	admin	2022-09-15T11:37:48.140756800+0000	f	KCFRj8aRZnRRGUAQenSCqYA3AipV8cRi	\N	3	bearer
76	2022-09-15 14:57:59.244	admin	2022-09-15 15:16:06.421	admin	2022-09-15T15:00:59.228244400+0000	f	OLisrjVZSnItLOwIHCD0JkPSiaL7hZc4	\N	3	bearer
77	2022-09-15 15:16:06.421	admin	2022-09-15 16:43:35.612	admin	2022-09-15T15:19:06.411338600+0000	f	MN4zudKLXj62XCFwUFqxDabuXUlbZAyf	\N	3	bearer
78	2022-09-15 16:43:35.61	admin	2022-09-15 16:47:33.366	admin	2022-09-15T16:46:35.596492800+0000	f	kcYY5KiS3U5WN9XvySTecYXwkChEOlwH	\N	3	bearer
79	2022-09-15 16:47:33.363	admin	2022-09-15 16:56:26.191	admin	2022-09-15T16:50:33.361452900+0000	f	2PC4UqHe7lxS0T2hQ4Z7uGblXJdNhlKI	\N	3	bearer
80	2022-09-15 16:56:26.188	admin	2022-09-15 17:36:36.814	admin	2022-09-15T16:59:26.176348100+0000	f	rqeebH2LcT4Yn09fBDWHrxFIlxBNSMvZ	\N	3	bearer
81	2022-09-15 17:36:36.812	admin	2022-09-15 17:43:25.679	admin	2022-09-15T17:39:36.788851100+0000	f	7lscIlFnK4qFwbAVTpMOKdfjNhwaN6Ig	\N	3	bearer
82	2022-09-15 17:43:25.663	admin	2022-09-15 17:47:23.432	admin	2022-09-15T17:46:25.663889400+0000	f	E5irEeALtqr86QQoGUy55jKHA6ad67f1	\N	3	bearer
83	2022-09-15 17:47:23.432	admin	2022-09-15 17:51:35.214	admin	2022-09-15T17:50:23.401348200+0000	f	FwBnWEq8ykcIC2vAYpZ3sRQ4rd5hfaB4	\N	3	bearer
84	2022-09-15 17:51:35.198	admin	2022-09-15 19:35:06.436	admin	2022-09-15T17:54:35.185800900+0000	f	whlFgB91lryccBjNdUO6g85zRt3epihr	\N	3	bearer
85	2022-09-15 19:35:06.431	admin	2022-09-15 19:45:50.275	admin	2022-09-15T19:38:06.429648600+0000	f	UwLBR85fRnw0ydIcCCYVNX3wiyuLJmUy	\N	3	bearer
86	2022-09-15 19:45:50.259	admin	2022-09-15 19:52:07.102	admin	2022-09-15T19:48:50.259760400+0000	f	uE2i8qtNo6ShqMPLIdPWHxTxcxYlcrXm	\N	3	bearer
87	2022-09-15 19:52:07.102	admin	2022-09-16 09:01:04.864	admin	2022-09-15T19:55:07.102260400+0000	f	8nHWMDsoew9Ogu6DiXNmirbAh1vZY7eY	\N	3	bearer
88	2022-09-16 09:01:04.864	admin	2022-09-16 09:07:48.794	admin	2022-09-16T09:04:04.834748600+0000	f	WLKziwNQko259vYRWsBhRUMrOPulqiY1	\N	3	bearer
89	2022-09-16 09:07:48.792	admin	2022-09-16 09:34:03.959	admin	2022-09-16T09:10:48.776004+0000	f	1E07dWqggB0hcX31HjAvx0eLdxXcucS0	\N	3	bearer
90	2022-09-16 09:34:03.959	admin	2022-09-16 09:48:15.692	admin	2022-09-16T09:37:03.959021500+0000	f	WYKD0MUbBCNOEE3H9eQ56NDhJksLH7W3	\N	3	bearer
91	2022-09-16 09:48:15.688	admin	2022-09-16 10:03:47.749	admin	2022-09-16T09:51:15.660770800+0000	f	YanoTSXPQdxAK7DRneAKl4csylhWw8I2	\N	3	bearer
92	2022-09-16 10:03:47.744	admin	2022-09-16 10:42:04.973	admin	2022-09-16T10:06:47.717128400+0000	f	5om37f13QPpO6wclPn0e9wrMeq83fj6Z	\N	3	bearer
93	2022-09-16 10:42:04.961	admin	2022-09-17 20:29:30.678	admin	2022-09-16T10:45:04.941748100+0000	f	SXuKFZxZJVVqJdNGUAS6pWnlIvsQh9Cz	\N	3	bearer
94	2022-09-17 20:29:30.678	admin	2022-09-17 20:43:07.556	admin	2022-09-17T20:32:30.662652400+0000	f	Fuci6SKQ37wiYpns4b0OjISL9ZBVDxaX	\N	3	bearer
95	2022-09-17 20:43:07.55	admin	2022-09-17 20:48:51.648	admin	2022-09-17T20:46:07.534494+0000	f	gfAYbMSJ9XZrq1Bo9l78hciI7XCq9T2Y	\N	3	bearer
96	2022-09-17 20:48:51.648	admin	2022-09-17 20:55:59.493	admin	2022-09-17T20:51:51.631602900+0000	f	7skZpy6N5g9OV3hhS5PSafGp5pqQ78AL	\N	3	bearer
97	2022-09-17 20:55:59.493	admin	2022-09-17 21:04:24.911	admin	2022-09-17T20:58:59.477900900+0000	f	CbyRuWTBFQstjZUylQLdENox4nd36dGT	\N	3	bearer
99	2022-09-19 14:27:03.825	admin	2022-09-19 15:08:56.025	admin	2022-09-19T14:30:03.815512600+0000	f	2isWrbIFGFzByqhbQ2P28t3IhuNsGXNR	\N	3	bearer
98	2022-09-17 21:04:24.911	admin	2022-09-19 14:27:03.825	admin	2022-09-17T21:07:24.896083900+0000	f	012OuoFjtRdSuY4hb5d8s4x2cjaK0rdf	\N	3	bearer
100	2022-09-19 15:08:56.02	admin	2022-09-20 19:47:06.936	admin	2022-09-19T15:11:56.018569600+0000	f	9wYEgBsnyaSNGaFr5cOdhSKYbcafpW1Q	\N	3	bearer
101	2022-09-20 19:47:06.919	admin	2022-10-15 12:59:40.925	admin	2022-09-20T19:50:06.888153500+0000	f	83TvNBDifDJgEx7LhqMlRekafTGtwpXf	\N	3	bearer
102	2022-10-15 12:59:40.913	admin	2022-12-17 19:24:29.095	admin	2022-10-15T13:02:40.877354800+0000	f	hNN0J0gd4xenlGVUQ9lI9lCCyNCLf9MM	\N	3	bearer
103	2022-12-17 19:24:29.081	admin	2022-12-17 19:35:42.858	admin	2022-12-17T19:27:29.063509400+0000	f	d9Q564dcIpy4MEBxJ5AUkU40q4afPOUQ	\N	3	bearer
104	2022-12-17 19:35:42.842	admin	2022-12-17 19:45:38.797	admin	2022-12-17T19:38:42.842591+0000	f	LgfAdAvsf3MuByTFrFEUk3gOIdy7FB1p	\N	3	bearer
105	2022-12-17 19:45:38.794	admin	2022-12-17 19:50:05.722	admin	2022-12-17T19:48:38.791954300+0000	f	50T58xhkisn2lZGAQNWPj1Ofd8zQx5pr	\N	3	bearer
106	2022-12-17 19:50:05.722	admin	2022-12-18 01:19:33.992	admin	2022-12-17T19:53:05.697552600+0000	f	LS9CPhF31zzriY3f5ghVhA5clXU47n5c	\N	3	bearer
107	2022-12-18 01:19:33.975	admin	2022-12-18 01:34:31.248	admin	2022-12-18T01:22:33.944763400+0000	f	Y3w3qngaOFUuYXLxjrYuM4jgBfWT5dd3	\N	3	bearer
108	2022-12-18 01:34:31.248	admin	2022-12-18 01:37:51.079	admin	2022-12-18T01:37:31.248629600+0000	f	Dy1ohfSKZkamHd50TAVH3T6bf2AkP7dZ	\N	3	bearer
109	2022-12-18 01:37:51.063	admin	2022-12-18 01:50:29.433	admin	2022-12-18T01:40:51.031326900+0000	f	i6RZ75uSur3OrOWdfDIBTt0ljYuffJlH	\N	3	bearer
110	2022-12-18 01:50:29.417	admin	2022-12-18 01:53:58.531	admin	2022-12-18T01:53:29.417750100+0000	f	j2iNDJNW9TTV5Yh3yGkcwZMeuQVGLPhB	\N	3	bearer
111	2022-12-18 01:53:58.523	admin	2022-12-18 01:59:46.492	admin	2022-12-18T01:56:58.523101900+0000	f	DpC8Jfv6896L9xy7MU5H04aV5Knmcg9D	\N	3	bearer
112	2022-12-18 01:59:46.484	admin	2022-12-18 02:03:15.815	admin	2022-12-18T02:02:46.484929600+0000	f	t7woqMeh3EBFXQZ9pWb4h9It412057oz	\N	3	bearer
113	2022-12-18 02:03:15.815	admin	2022-12-18 02:07:52.686	admin	2022-12-18T02:06:15.815676800+0000	f	DN4xKPBygQCKLdVHZL5gFdkM5yAAk78w	\N	3	bearer
114	2022-12-18 02:07:52.686	admin	2022-12-18 11:07:17.671	admin	2022-12-18T02:10:52.678559500+0000	f	lPd6P2lvq7jyEWsZVmHSOyCSx36XWXJx	\N	3	bearer
115	2022-12-18 11:07:17.655	admin	2022-12-18 11:17:03.005	admin	2022-12-18T11:10:17.655421+0000	f	Y9TMWGY9G00a4z1sQr6v2TdUSqpY2dC7	\N	3	bearer
116	2022-12-18 11:17:02.991	admin	2022-12-18 11:27:22.792	admin	2022-12-18T11:20:02.974238900+0000	f	oEw8IL1Fgvh73ChkfrQMULor1a088ANZ	\N	3	bearer
117	2022-12-18 11:27:22.789	admin	2022-12-18 11:35:54.335	admin	2022-12-18T11:30:22.786640600+0000	f	Pi2x0AYInPRVhf6BZ9CidzwlVL1kfyG2	\N	3	bearer
118	2022-12-18 11:35:54.332	admin	2022-12-18 11:59:22.389	admin	2022-12-18T11:38:54.329047900+0000	f	y8XVVT9k5ug9nlxVnfzRi09uYWyWiKue	\N	3	bearer
119	2022-12-18 11:59:22.389	admin	2022-12-18 12:27:37.549	admin	2022-12-18T12:02:22.358398300+0000	f	rMcCwwZBXVylR6JMBnwQK3221lY4L1Yk	\N	3	bearer
120	2022-12-18 12:27:37.533	admin	2022-12-23 17:35:21.802	admin	2022-12-18T12:30:37.502146100+0000	f	B1e1Tn5nCfN2XevKSjYFpWhInk084S5i	\N	3	bearer
121	2022-12-23 17:35:21.782	admin	2022-12-24 22:04:59.377	admin	2022-12-23T17:38:21.751054300+0000	f	yQ0wYT3in9JYN3ryXGPqmTPJqjpCT4el	\N	3	bearer
122	2022-12-24 22:04:59.353	admin	2023-01-07 17:33:06.42	admin	2022-12-24T22:07:59.306973100+0000	f	UaYLnzICMQcNoix9b1o3Ktk3SglnRbui	\N	3	bearer
123	2023-01-07 17:33:06.411	admin	2023-01-07 17:37:51.176	admin	2023-01-07T17:36:06.400241+0000	f	x9GUG7oa2qUKPepiZQ4fhCiwfcBWswsx	\N	3	bearer
124	2023-01-07 17:37:52.28	admin	2023-01-07 18:21:44.405	admin	2023-01-07T17:40:52.260105700+0000	f	HMufCgzSadenu8qRP7eleZDCan3n3kXK	\N	3	bearer
125	2023-01-07 18:21:45.555	admin	2023-01-07 19:25:56.245	admin	2023-01-07T18:24:45.541122800+0000	f	WCMPZWjCxZ5HDCUAl0L2mK0B3hCCSMtX	\N	3	bearer
126	2023-01-07 19:25:57.588	admin	2023-01-07 19:30:28.504	admin	2023-01-07T19:28:57.576541500+0000	f	ow5yKivTcDLNygmmRd2oRfqrt5g46cRH	\N	3	bearer
127	2023-01-07 19:30:29.716	admin	2023-01-08 00:01:52.946	admin	2023-01-07T19:33:29.704131600+0000	f	Yha5Ayk93jxLjdVLs9X77kafZ6VmPEZA	\N	3	bearer
128	2023-01-08 00:01:59.12	admin	2023-01-08 00:07:35.622	admin	2023-01-08T00:04:59.104497200+0000	f	pLf7ux2jMOqYlm819alyNpTiCwWbsE8R	\N	3	bearer
129	2023-01-08 00:07:37.048	admin	2023-01-08 00:23:16.927	admin	2023-01-08T00:10:37.032615700+0000	f	5o4VUguO2lNpjFioe22lvuLo17djwpxD	\N	3	bearer
130	2023-01-08 00:23:19.987	admin	2023-01-08 00:30:32.343	admin	2023-01-08T00:26:19.984090400+0000	f	EdEHDHqRhCcI3kr0OV08l7Qw0mCuGl4m	\N	3	bearer
131	2023-01-08 00:30:34.341	admin	2023-01-08 00:33:43.418	admin	2023-01-08T00:33:34.333445500+0000	f	L9G3VtOpZ6DYBRZIMPBIfcrpO3RI7Ubd	\N	3	bearer
132	2023-01-08 00:33:45.969	admin	2023-01-08 00:37:03.771	admin	2023-01-08T00:36:45.961828700+0000	f	cVaWE7yRZ4lIgZJhLdZvtxGBGwNhRVie	\N	3	bearer
133	2023-01-08 00:37:08.315	admin	2023-01-08 00:41:39.268	admin	2023-01-08T00:40:08.307604500+0000	f	HDsSMbbiUGlSZmeN8T5It2ZOSbqkVaVt	\N	3	bearer
136	2023-01-08 12:45:42.069	admin	2023-01-08 13:44:51.341	admin	2023-01-08T12:48:42.054350400+0000	f	lNVUyb15GUGm5X5FVIM6Njjx0eyBz8bN	\N	3	bearer
134	2023-01-08 00:41:47.446	admin	2023-01-08 12:33:46.013	admin	2023-01-08T00:44:47.438188400+0000	f	WeOksNhyAUsQ8ElllxrQlE3VHUIHuqOU	\N	3	bearer
135	2023-01-08 12:33:51.814	admin	2023-01-08 12:45:39.198	admin	2023-01-08T12:36:51.806422+0000	f	sDqV46IMxc3JSwGNWUC2e8n7oryXd0VL	\N	3	bearer
137	2023-01-08 13:44:54.41	admin	2023-01-08 14:33:07.601	anonymousUser	2023-01-08T13:47:54.394270600+0000	f	atxQAJRoDd8zRoTZeOXbBLGiBZTI4vnC	\N	3	bearer
138	2023-01-08 14:33:09.266	anonymousUser	2023-01-13 00:05:07.319	anonymousUser	2023-01-08T14:36:09.250547400+0000	f	lohodEHjy0wTrFKWt0EwVgoHL6cNuV1y	\N	3	bearer
139	2023-01-13 00:05:13.416	anonymousUser	2023-01-13 00:57:46.535	anonymousUser	2023-01-13T00:08:13.409171400+0000	f	FSRCKzqqLEdj6jULs38t2la9rcwBWfPj	\N	3	bearer
140	2023-01-13 00:57:57.273	anonymousUser	2023-01-13 09:55:46.958	anonymousUser	2023-01-13T01:00:57.269747400+0000	f	YAvssg56iZABqm0nqfl1HwSZCCl7l33D	\N	3	bearer
141	2023-01-13 09:55:49.688	anonymousUser	2023-01-20 18:05:18.126	anonymousUser	2023-01-13T09:58:49.674714+0000	f	MdymnKKMSWoFC8AhAW3Jintbv5bFeZqi	\N	3	bearer
142	2023-01-20 18:05:21.272	anonymousUser	2024-09-16 12:15:55.651	admin	2023-01-20T18:08:21.272516300+0000	f	OfWx8fwXaCh6ooWho9haqAOKqKD65Vv8	\N	3	bearer
143	2024-09-16 12:15:58.73	admin	2024-09-16 12:15:58.73	admin	2024-09-16T12:18:58.706832600+0000	t	jNS3ttwaIvuvdS2K7XA3NpIgV6l8QCyT	\N	3	bearer
\.


--
-- Data for Name: session_tontine; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.session_tontine (id_session_tontine, created_at, created_by, modified_at, modified_by, date_debut, date_fin, description, libelle, montant, owner_id_user, tontine_id_tontine, total_collecte) FROM stdin;
1	2023-01-12 15:55:35.893	admin	2023-01-12 15:55:35.893	admin	2023-01-01	2023-01-31		Session Janvier 2023	20000	\N	1	0
2	2023-01-12 21:26:30.761	admin	2023-01-12 21:26:30.761	admin	\N	\N	\N	\N	20000	\N	\N	0
3	2023-01-13 09:26:44.67	Abou	2023-01-13 09:26:44.67	Abou	2023-01-01	2023-01-27	\N	Versement Janvier 2023	5000	\N	3	5000
\.


--
-- Data for Name: session_tontine_transaction; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.session_tontine_transaction (id_session_tontine_transaction, created_at, created_by, modified_at, modified_by, code, montant, statut, evenement_id_evenement, membre_id_membre, partenaire_id_partenaire, session_tontine_id_session_tontine, transaction_id) FROM stdin;
2	2023-01-12 21:53:53.943	admin	2023-01-12 21:53:53.943	admin	2465	20000	INITIEE	\N	4	3	1	37
1	2023-01-12 21:34:12.408	admin	2023-01-12 21:34:12.408	admin	2465	20000	INITIEE	\N	5	3	1	36
3	2023-01-13 09:36:45.584	Abou	2023-01-13 09:36:45.584	Abou	5505	5000	INITIEE	\N	7	3	3	40
\.


--
-- Data for Name: tontine; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tontine (id_tontine, created_at, created_by, modified_at, modified_by, bouton, description, libelle, lien, montant, periodicite, photo_url, owner_id_user) FROM stdin;
1	2023-01-11 19:51:11.826	admin	2023-01-11 19:51:11.826	admin	Cotiser	Projet agagi vise à aider les jeunes entrepreuneurs à booster leur business.	Tontime Agagi		20000	\N	c30bfeaa-40b3-4427-9be5-c02eb739c2a6-WhatsApp Image 2023-01-10 at 10.57.38.jpeg	1
2	2023-01-13 00:59:24.282	Abou	2023-01-13 00:59:24.282	Abou	Cotiser	Description	Tontine MATA		5000	\N	053f5f54-26df-42f5-a2cc-111f9e7c1de1-MySawki.png	8
3	2023-01-13 09:23:42.969	Abou	2023-01-13 09:23:42.969	Abou	Cotiser	Un projet de collecte pour les jeunes	Assoussou Matassa		5000	\N	a7267443-0159-449c-8fd1-211342be1b84-undraw_Software_engineer_re_fyew.png	8
\.


--
-- Data for Name: transaction; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.transaction (id_transaction, code, libelle_transaction, montant, nom_client, numero, prenom_client, app_client_id, evenement_id_evenement, partenaire_id_partenaire, type_transaction_id_type_transaction, response_enquiry, statut, created_at, created_by, modified_at, modified_by) FROM stdin;
1	\N	BEPAY EVENT 1	100	BAKO ADAMOU	98793379		\N	1	3	\N	\N	\N	\N	\N	\N	\N
2	\N	BEPAY EVENT 2	100	BAKO ADAMOU	98793379		\N	2	3	\N	\N	\N	\N	\N	\N	\N
3	\N	BEPAY EVENT 2	100	BAKO ADAMOU	98793379		\N	2	3	\N	{"data":{"transaction":{"airtel_money_id":"MP221224.2205.C00292","id":"3","message":"Desole, le mot passe entre est incorrecte.","status":"TF"}},"status":{"response_code":"DP008001013","code":"200","success":true,"result_code":"ESB000010","message":"SUCCESS"}}	\N	\N	\N	\N	\N
4	\N	BEPAY EVENT 2	100	Moubarak Bako	98793379		\N	2	3	\N	\N	INITIEE	\N	\N	\N	\N
24	\N	BEPAY EVENT 2	100	Abou Sayabou	98793379		\N	2	3	\N	\N	EN COURS	\N	\N	\N	\N
7	\N	BEPAY EVENT 2	100	Moubarak Bako	98793379		\N	2	3	\N	{"data":{"transaction":{"airtel_money_id":"MP230107.1738.J04514","id":"7","message":"Your transaction has been successfully processed","status":"TS"}},"status":{"response_code":"DP00800001006","code":"200","success":true,"result_code":"ESB000010","message":"SUCCESS"}}	EN COURS	\N	\N	\N	\N
8	\N	BEPAY EVENT 2	100	Elh Bako Ara	98793379		\N	2	3	\N	\N	EN COURS	\N	\N	\N	\N
9	\N	BEPAY EVENT 2	100	Elh Bako Ara	98793379		\N	2	3	\N	\N	EN COURS	\N	\N	\N	\N
17	\N	BEPAY EVENT 2	100	Moubarak	98793379		\N	2	3	\N	{"data":{"transaction":{"id":"17","status":"TIP"}},"status":{"response_code":"DP00800001006","code":"200","success":true,"result_code":"ESB000010","message":"SUCCESS"}}	EN COURS	\N	\N	\N	\N
25	\N	BEPAY EVENT 2	100	Abou Sayabou	98793379		\N	2	3	\N	\N	EN COURS	\N	\N	\N	\N
18	\N	BEPAY EVENT 2	50	Moubarak	98793379		\N	2	3	\N	{"data":{"transaction":{"airtel_money_id":"MP230107.1930.J06059","id":"18","message":"Le montant de la transaction est inferieur au montant minimum defini pour ce service","status":"TF"}},"status":{"response_code":"DP008001013","code":"200","success":true,"result_code":"ESB000010","message":"SUCCESS"}}	EN COURS	\N	\N	\N	\N
19	\N	BEPAY EVENT 2	100	Ibrahim Magagi	98793379		\N	2	3	\N	{"data":{"transaction":{"airtel_money_id":"MP230108.0002.J08752","id":"19","message":"Your transaction has been successfully processed","status":"TS"}},"status":{"response_code":"DP00800001006","code":"200","success":true,"result_code":"ESB000010","message":"SUCCESS"}}	EN COURS	\N	\N	\N	\N
31	\N	BEPAY EVENT 2	100	Ibrahim ISSA	98793379		\N	2	3	\N	{"data":{"transaction":{"airtel_money_id":"MP230108.1247.C05644","id":"31","message":"Your transaction has been successfully processed","status":"TS"}},"status":{"response_code":"DP00800001006","code":"200","success":true,"result_code":"ESB000010","message":"SUCCESS"}}	TERMINEE	\N	\N	\N	\N
20	\N	BEPAY EVENT 2	100	Ibrahim	98793379		\N	2	3	\N	{"data":{"transaction":{"airtel_money_id":"MP230108.0007.J08781","id":"20","message":"Your transaction has been successfully processed","status":"TS"}},"status":{"response_code":"DP00800001006","code":"200","success":true,"result_code":"ESB000010","message":"SUCCESS"}}	EN COURS	\N	\N	\N	\N
26	\N	BEPAY EVENT 2	100	Abou Sayabou	98793379		\N	2	3	\N	{"data":{"transaction":{"airtel_money_id":"MP230108.0042.J08849","id":"26","message":"Your transaction has been successfully processed","status":"TS"}},"status":{"response_code":"DP00800001006","code":"200","success":true,"result_code":"ESB000010","message":"SUCCESS"}}	EN COURS	\N	\N	\N	\N
21	\N	BEPAY EVENT 2	100	Mamane Ara	98793379		\N	2	3	\N	{"data":{"transaction":{"airtel_money_id":"MP230108.0023.C02696","id":"21","message":"Your transaction has been successfully processed","status":"TS"}},"status":{"response_code":"DP00800001006","code":"200","success":true,"result_code":"ESB000010","message":"SUCCESS"}}	EN COURS	\N	\N	\N	\N
22	\N	BEPAY EVENT 2	100	Ibrahim ISSA	98793379		\N	2	3	\N	{"data":{"transaction":{"airtel_money_id":"MP230108.0030.C02726","id":"22","message":"Your transaction has been successfully processed","status":"TS"}},"status":{"response_code":"DP00800001006","code":"200","success":true,"result_code":"ESB000010","message":"SUCCESS"}}	EN COURS	\N	\N	\N	\N
23	\N	BEPAY EVENT 2	100	Ibrahim ISSA	98793379		\N	2	3	\N	{"data":{"transaction":{"airtel_money_id":"MP230108.0034.C02732","id":"23","message":"Your transaction has been successfully processed","status":"TS"}},"status":{"response_code":"DP00800001006","code":"200","success":true,"result_code":"ESB000010","message":"SUCCESS"}}	EN COURS	\N	\N	\N	\N
29	\N	BEPAY EVENT 2	100	Ibrahim ISSA	98793379		\N	2	3	\N	{"data":{"transaction":{"airtel_money_id":"MP230108.1234.J00934","id":"29","message":"Your transaction has been successfully processed","status":"TS"}},"status":{"response_code":"DP00800001006","code":"200","success":true,"result_code":"ESB000010","message":"SUCCESS"}}	EN COURS	\N	\N	\N	\N
30	\N	BEPAY EVENT 2	100	Ibrahim ISSA	98793379		\N	2	3	\N	{"data":{"transaction":{"airtel_money_id":"MP230108.1245.C05617","id":"30","message":"Your transaction has been successfully processed","status":"TS"}},"status":{"response_code":"DP00800001006","code":"200","success":true,"result_code":"ESB000010","message":"SUCCESS"}}	EN COURS	\N	\N	\N	\N
36	\N	\N	20000	Issa	98793379	Moussa	\N	\N	3	\N	\N	INITIEE	2023-01-12 21:34:12.425	admin	2023-01-12 21:34:12.425	admin
34	\N	BEPAY EVENT 2	100	Ibrahim ISSA	98793379		\N	2	3	\N	{"data":{"transaction":{"id":"34","status":"TIP"}},"status":{"response_code":"DP00800001006","code":"200","success":true,"result_code":"ESB000010","message":"SUCCESS"}}	TERMINEE	\N	\N	\N	\N
32	\N	BEPAY EVENT 2	100	Ibrahim ISSA	98793379		\N	2	3	\N	{"data":{"transaction":{"airtel_money_id":"MP230108.1345.J01558","id":"32","message":"Your transaction has been successfully processed","status":"TS"}},"status":{"response_code":"DP00800001006","code":"200","success":true,"result_code":"ESB000010","message":"SUCCESS"}}	TERMINEE	\N	\N	\N	\N
33	\N	BEPAY EVENT 2	100	Ibrahim ISSA	98793379		\N	2	3	\N	{"data":{"transaction":{"id":"33","status":"TIP"}},"status":{"response_code":"DP00800001006","code":"200","success":true,"result_code":"ESB000010","message":"SUCCESS"}}	TERMINEE	\N	\N	\N	\N
37	\N	\N	20000	Issa	98792003	Moussa	\N	\N	3	\N	\N	INITIEE	2023-01-12 21:53:53.958	admin	2023-01-12 21:53:53.958	admin
35	\N	BEPAY EVENT 2	100	BAKO ADAMOU	98793379		\N	2	3	\N	{"data":{"transaction":{"airtel_money_id":"MP230108.1435.J01958","id":"35","message":"Your transaction has been successfully processed","status":"TS"}},"status":{"response_code":"DP00800001006","code":"200","success":true,"result_code":"ESB000010","message":"SUCCESS"}}	TERMINEE	\N	\N	\N	\N
38	\N	BEPAY EVENT 6	100	Ibrahim ISSA	98793379		\N	6	3	\N	{"data":{"transaction":{"airtel_money_id":"MP230113.0005.C06976","id":"38","message":"Your transaction has been successfully processed","status":"TS"}},"status":{"response_code":"DP00800001006","code":"200","success":true,"result_code":"ESB000010","message":"SUCCESS"}}	TERMINEE	2023-01-13 00:05:07.195	anonymousUser	2023-01-13 00:06:04.669	anonymousUser
39	\N	BEPAY EVENT 6	100	Ibrahim ISSA	98793379		\N	6	3	\N	{"data":{"transaction":{"airtel_money_id":"MP230113.0058.J04754","id":"39","message":"Your transaction has been successfully processed","status":"TS"}},"status":{"response_code":"DP00800001006","code":"200","success":true,"result_code":"ESB000010","message":"SUCCESS"}}	TERMINEE	2023-01-13 00:57:46.474	anonymousUser	2023-01-13 00:58:20.517	anonymousUser
40	\N	\N	5000	Saley	98793379	Farida	\N	\N	3	\N	\N	INITIEE	2023-01-13 09:36:45.598	Abou	2023-01-13 09:36:45.598	Abou
42	\N	BEPAY EVENT 6	100	Ibrahim ISSA	98793379		\N	6	3	\N	{"data":{"transaction":{"airtel_money_id":"MP230113.0956.J05048","id":"42","message":"Your transaction has been successfully processed","status":"TS"}},"status":{"response_code":"DP00800001006","code":"200","success":true,"result_code":"ESB000010","message":"SUCCESS"}}	TERMINEE	2023-01-13 09:55:46.901	anonymousUser	2023-01-13 09:56:10.219	anonymousUser
43	\N	BEPAY EVENT 4	100	BAKO ADAMOU	98793379		\N	4	3	\N	{"data":{"transaction":{"id":"43","status":"TIP"}},"status":{"response_code":"DP00800001006","code":"200","success":true,"result_code":"ESB000010","message":"SUCCESS"}}	TERMINEE	2023-01-20 18:05:18.022	anonymousUser	2023-01-20 18:07:09.75	anonymousUser
44	122002	Cotisation	200	BAKO	98793379	Adamou	\N	\N	3	1	\N	EN COURS	2024-09-16 12:15:55.396	admin	2024-09-16 12:15:55.396	admin
\.


--
-- Data for Name: type_action; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.type_action (id_type_action, libelle_type_action) FROM stdin;
1	GetOne
2	GetList
3	PostOne
\.


--
-- Data for Name: type_reponse; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.type_reponse (id_type_reponse, libelle_type_reponse, type) FROM stdin;
1	JSON3	3
\.


--
-- Data for Name: type_transaction; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.type_transaction (id_type_transaction, libelle_type_transaction) FROM stdin;
1	Payement
2	Remboursement
\.


--
-- Data for Name: utilisateur; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.utilisateur (id_user, created_at, created_by, modified_at, modified_by, email, nom, password, prenom, username, profile_id, statut, telephone) FROM stdin;
2	2022-08-30 17:33:01.444	admin	2022-08-30 18:10:27.822	admin	eeeeeeeeee	nnnnnnnnnnn	$2a$10$RY1fXP9FMyNmj5d6wr7JvurosKSnHrxeW0rYxYQnHFuYPhwIbrnEC	pppppppppppp	user	2	\N	\N
3	2022-08-31 10:49:06.462	admin	2022-08-31 10:50:16.701	admin	emailU	nomU	$2a$10$toWxkTT3NiAAZDxmB49x0ubtEkXg8T7gham.brpsnJzkFBZSrcUWO	prenomU	loginUser	2	\N	\N
4	2022-08-31 10:51:19.296	admin	2022-08-31 10:51:19.296	admin	tn	tn	$2a$10$tIF2PIWuYEJf7A0Bb50IH.Arh4JpBkb48WVXuftyHMaxbj/uo1T7.	tds	tnr	1	\N	\N
5	2022-08-31 10:52:43.97	admin	2022-08-31 10:52:43.97	admin	bj	bjmk	$2a$10$VWTH8.IIHP/qW1KdTtDyt.3C7fnNbGdtShBRG1J1Kka88mD79I1GW	hum	bnjkm	1	\N	\N
6	2022-09-02 14:30:02.982	admin	2022-09-02 14:30:02.982	admin	beit@gmail.com	beit	$2a$10$HrLiqv0Nl0OGJStClXeS6.trWKUQImAryiXolaPaTMKxOA37S2VK6	beit	beit	2	\N	\N
7	2022-12-26 23:58:23.831	anonymousUser	2022-12-26 23:58:23.831	anonymousUser	issa.moussa@gmail.com	ISSA MOUSSA	$2a$10$YZPRTeNbEqGyNXwYoKB8Ee9kT1/Kr6c7B4iNzU7UtKP194OBe9GBG	\N	Issamouss	3	f	90299039
1	\N	\N	\N	\N	\N	BAKO ADAMOU	$2a$10$eu1XEIEAWgd6k8pR7Dv/GumISEiDkwJyo0h6qDd5RCKR15alejJiq	M.Bariki	admin	1	\N	\N
8	2023-01-12 22:02:27.569	anonymousUser	2023-01-12 22:02:27.569	anonymousUser	abou@gmail.com	Abou	$2a$10$ZXV6/OmyIruHGJar0vOdmOUsBUJkYU1iYwJO3Vtg/L58R75CTlewW	\N	Abou	3	f	94107074
\.


--
-- Name: api_id_api_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.api_id_api_seq', 6, true);


--
-- Name: app_client_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.app_client_id_seq', 5, true);


--
-- Name: application_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.application_id_seq', 5, true);


--
-- Name: evenement_id_evenement_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.evenement_id_evenement_seq', 8, true);


--
-- Name: membre_id_membre_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.membre_id_membre_seq', 7, true);


--
-- Name: parametre_id_parametre_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.parametre_id_parametre_seq', 98, true);


--
-- Name: partenaire_id_partenaire_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.partenaire_id_partenaire_seq', 5, true);


--
-- Name: possible_values_id_possible_value_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.possible_values_id_possible_value_seq', 30, true);


--
-- Name: profile_id_profile_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.profile_id_profile_seq', 3, true);


--
-- Name: reponse_param_id_reponse_param_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.reponse_param_id_reponse_param_seq', 12, true);


--
-- Name: session_id_session_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.session_id_session_seq', 143, true);


--
-- Name: session_tontine_id_session_tontine_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.session_tontine_id_session_tontine_seq', 3, true);


--
-- Name: session_tontine_transaction_id_session_tontine_transaction_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.session_tontine_transaction_id_session_tontine_transaction_seq', 3, true);


--
-- Name: tontine_id_tontine_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tontine_id_tontine_seq', 3, true);


--
-- Name: transaction_id_transaction_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.transaction_id_transaction_seq', 44, true);


--
-- Name: type_action_id_type_action_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.type_action_id_type_action_seq', 3, true);


--
-- Name: type_reponse_id_type_reponse_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.type_reponse_id_type_reponse_seq', 1, true);


--
-- Name: type_transaction_id_type_transaction_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.type_transaction_id_type_transaction_seq', 2, true);


--
-- Name: utilisateur_id_user_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.utilisateur_id_user_seq', 8, true);


--
-- Name: api api_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.api
    ADD CONSTRAINT api_pkey PRIMARY KEY (id_api);


--
-- Name: app_client app_client_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.app_client
    ADD CONSTRAINT app_client_pkey PRIMARY KEY (id);


--
-- Name: application application_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.application
    ADD CONSTRAINT application_pkey PRIMARY KEY (id);


--
-- Name: evenement evenement_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.evenement
    ADD CONSTRAINT evenement_pkey PRIMARY KEY (id_evenement);


--
-- Name: membre membre_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.membre
    ADD CONSTRAINT membre_pkey PRIMARY KEY (id_membre);


--
-- Name: parametre parametre_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.parametre
    ADD CONSTRAINT parametre_pkey PRIMARY KEY (id_parametre);


--
-- Name: partenaire partenaire_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.partenaire
    ADD CONSTRAINT partenaire_pkey PRIMARY KEY (id_partenaire);


--
-- Name: possible_values possible_values_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.possible_values
    ADD CONSTRAINT possible_values_pkey PRIMARY KEY (id_possible_value);


--
-- Name: profile profile_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profile
    ADD CONSTRAINT profile_pkey PRIMARY KEY (id_profile);


--
-- Name: reponse_param reponse_param_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reponse_param
    ADD CONSTRAINT reponse_param_pkey PRIMARY KEY (id_reponse_param);


--
-- Name: session session_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.session
    ADD CONSTRAINT session_pkey PRIMARY KEY (id_session);


--
-- Name: session_tontine session_tontine_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.session_tontine
    ADD CONSTRAINT session_tontine_pkey PRIMARY KEY (id_session_tontine);


--
-- Name: session_tontine_transaction session_tontine_transaction_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.session_tontine_transaction
    ADD CONSTRAINT session_tontine_transaction_pkey PRIMARY KEY (id_session_tontine_transaction);


--
-- Name: tontine tontine_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tontine
    ADD CONSTRAINT tontine_pkey PRIMARY KEY (id_tontine);


--
-- Name: transaction transaction_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transaction
    ADD CONSTRAINT transaction_pkey PRIMARY KEY (id_transaction);


--
-- Name: type_action type_action_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.type_action
    ADD CONSTRAINT type_action_pkey PRIMARY KEY (id_type_action);


--
-- Name: type_reponse type_reponse_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.type_reponse
    ADD CONSTRAINT type_reponse_pkey PRIMARY KEY (id_type_reponse);


--
-- Name: type_transaction type_transaction_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.type_transaction
    ADD CONSTRAINT type_transaction_pkey PRIMARY KEY (id_type_transaction);


--
-- Name: partenaire uk_2y49uli6lyu3o9j61st08ksc2; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.partenaire
    ADD CONSTRAINT uk_2y49uli6lyu3o9j61st08ksc2 UNIQUE (email);


--
-- Name: partenaire uk_3mabakalh5yvqx1n51ykjj6o0; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.partenaire
    ADD CONSTRAINT uk_3mabakalh5yvqx1n51ykjj6o0 UNIQUE (telephone);


--
-- Name: partenaire uk_k71tgg8833gk2uunbr5lidsp7; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.partenaire
    ADD CONSTRAINT uk_k71tgg8833gk2uunbr5lidsp7 UNIQUE (nom_compagnie);


--
-- Name: membre uk_ld6gdhj2rsvlj2f5euafcmm8e; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.membre
    ADD CONSTRAINT uk_ld6gdhj2rsvlj2f5euafcmm8e UNIQUE (telephone);


--
-- Name: tontine uk_ln8yiarb68wixy51cqopinagl; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tontine
    ADD CONSTRAINT uk_ln8yiarb68wixy51cqopinagl UNIQUE (libelle);


--
-- Name: evenement uk_nc0k0rmm5uk8ok8a0tmcfit4a; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.evenement
    ADD CONSTRAINT uk_nc0k0rmm5uk8ok8a0tmcfit4a UNIQUE (libelle);


--
-- Name: membre uk_o596rgsrax69i2f6ia0oo63ll; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.membre
    ADD CONSTRAINT uk_o596rgsrax69i2f6ia0oo63ll UNIQUE (code);


--
-- Name: utilisateur uk_rma38wvnqfaf66vvmi57c71lo; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utilisateur
    ADD CONSTRAINT uk_rma38wvnqfaf66vvmi57c71lo UNIQUE (email);


--
-- Name: utilisateur utilisateur_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utilisateur
    ADD CONSTRAINT utilisateur_pkey PRIMARY KEY (id_user);


--
-- Name: session fk1ljn38dshuil7id84dsjx3v9n; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.session
    ADD CONSTRAINT fk1ljn38dshuil7id84dsjx3v9n FOREIGN KEY (partenaire_id_partenaire) REFERENCES public.partenaire(id_partenaire);


--
-- Name: transaction fk2of13qefrxblm779ofoiyc19a; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transaction
    ADD CONSTRAINT fk2of13qefrxblm779ofoiyc19a FOREIGN KEY (evenement_id_evenement) REFERENCES public.evenement(id_evenement);


--
-- Name: tontine fk33d63aa8d0ino19g4x0fd6f58; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tontine
    ADD CONSTRAINT fk33d63aa8d0ino19g4x0fd6f58 FOREIGN KEY (owner_id_user) REFERENCES public.utilisateur(id_user);


--
-- Name: session_tontine_transaction fk39q0flpv8xdib4qqft5kut0oo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.session_tontine_transaction
    ADD CONSTRAINT fk39q0flpv8xdib4qqft5kut0oo FOREIGN KEY (membre_id_membre) REFERENCES public.membre(id_membre);


--
-- Name: utilisateur fk3at7367hsfq1i7ytn2b6sgt67; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utilisateur
    ADD CONSTRAINT fk3at7367hsfq1i7ytn2b6sgt67 FOREIGN KEY (profile_id) REFERENCES public.profile(id_profile);


--
-- Name: transaction fk6gkawgcnsnr65spgkl5lrdps1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transaction
    ADD CONSTRAINT fk6gkawgcnsnr65spgkl5lrdps1 FOREIGN KEY (type_transaction_id_type_transaction) REFERENCES public.type_transaction(id_type_transaction);


--
-- Name: api fk7i3v6t68e3k7bx9s65oeuwwe4; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.api
    ADD CONSTRAINT fk7i3v6t68e3k7bx9s65oeuwwe4 FOREIGN KEY (partenaire_id_partenaire) REFERENCES public.partenaire(id_partenaire);


--
-- Name: session_tontine_transaction fk7n2ceomtci73c2hwratrxe4o1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.session_tontine_transaction
    ADD CONSTRAINT fk7n2ceomtci73c2hwratrxe4o1 FOREIGN KEY (transaction_id) REFERENCES public.transaction(id_transaction);


--
-- Name: session_tontine_transaction fkatilmoo1b265w5o1r3d37tc8o; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.session_tontine_transaction
    ADD CONSTRAINT fkatilmoo1b265w5o1r3d37tc8o FOREIGN KEY (partenaire_id_partenaire) REFERENCES public.partenaire(id_partenaire);


--
-- Name: transaction fkbao02xrp5kdhfgm928wjpym24; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transaction
    ADD CONSTRAINT fkbao02xrp5kdhfgm928wjpym24 FOREIGN KEY (partenaire_id_partenaire) REFERENCES public.partenaire(id_partenaire);


--
-- Name: api fkfasptot5hpgyskwteygl9doi2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.api
    ADD CONSTRAINT fkfasptot5hpgyskwteygl9doi2 FOREIGN KEY (action_id_type_action) REFERENCES public.type_action(id_type_action);


--
-- Name: session_tontine_transaction fkfetn80erg4thom6xyw2xd8k72; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.session_tontine_transaction
    ADD CONSTRAINT fkfetn80erg4thom6xyw2xd8k72 FOREIGN KEY (session_tontine_id_session_tontine) REFERENCES public.session_tontine(id_session_tontine);


--
-- Name: evenement fkhll5exnomxssbh0sl4acxi49c; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.evenement
    ADD CONSTRAINT fkhll5exnomxssbh0sl4acxi49c FOREIGN KEY (user_id_user) REFERENCES public.utilisateur(id_user);


--
-- Name: transaction fkhynbshqes8xpjlx4ipfhi6g9t; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transaction
    ADD CONSTRAINT fkhynbshqes8xpjlx4ipfhi6g9t FOREIGN KEY (app_client_id) REFERENCES public.application(id);


--
-- Name: session fkilcauvtis328o0t1kxd0l8ij; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.session
    ADD CONSTRAINT fkilcauvtis328o0t1kxd0l8ij FOREIGN KEY (compagnie_id_partenaire) REFERENCES public.partenaire(id_partenaire);


--
-- Name: application fkiwd0iru5pdyhg0u1pw2tss1ro; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.application
    ADD CONSTRAINT fkiwd0iru5pdyhg0u1pw2tss1ro FOREIGN KEY (user_id_user) REFERENCES public.utilisateur(id_user);


--
-- Name: api fkj6939j4q5j435msgov243ysta; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.api
    ADD CONSTRAINT fkj6939j4q5j435msgov243ysta FOREIGN KEY (response_param_id_reponse_param) REFERENCES public.reponse_param(id_reponse_param);


--
-- Name: session_tontine fkj9r74an2iwlrmubwd98b8nuyn; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.session_tontine
    ADD CONSTRAINT fkj9r74an2iwlrmubwd98b8nuyn FOREIGN KEY (tontine_id_tontine) REFERENCES public.tontine(id_tontine);


--
-- Name: session_tontine_transaction fkjtmii0vs7jhpgoslje5kpjir6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.session_tontine_transaction
    ADD CONSTRAINT fkjtmii0vs7jhpgoslje5kpjir6 FOREIGN KEY (evenement_id_evenement) REFERENCES public.evenement(id_evenement);


--
-- Name: parametre fkjudhmpit4n84924s03ftxv7hn; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.parametre
    ADD CONSTRAINT fkjudhmpit4n84924s03ftxv7hn FOREIGN KEY (api_id_api) REFERENCES public.api(id_api);


--
-- Name: possible_values fkkngggil54l69gb67elg5soaiv; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.possible_values
    ADD CONSTRAINT fkkngggil54l69gb67elg5soaiv FOREIGN KEY (response_param_id_reponse_param) REFERENCES public.reponse_param(id_reponse_param);


--
-- Name: membres_tontines fkl2bqhfl25o281y5avfxbxf1rq; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.membres_tontines
    ADD CONSTRAINT fkl2bqhfl25o281y5avfxbxf1rq FOREIGN KEY (id_membre) REFERENCES public.membre(id_membre);


--
-- Name: api fkpf1f4ywca1vjo2xtyk7976yy1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.api
    ADD CONSTRAINT fkpf1f4ywca1vjo2xtyk7976yy1 FOREIGN KEY (reponse_id_type_reponse) REFERENCES public.type_reponse(id_type_reponse);


--
-- Name: membres_tontines fkqge8y02jdtad7xxsmdwrp28g4; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.membres_tontines
    ADD CONSTRAINT fkqge8y02jdtad7xxsmdwrp28g4 FOREIGN KEY (id_tontine) REFERENCES public.tontine(id_tontine);


--
-- Name: app_client fkqy92xodke271sy3rd5y369qn3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.app_client
    ADD CONSTRAINT fkqy92xodke271sy3rd5y369qn3 FOREIGN KEY (user_id) REFERENCES public.utilisateur(id_user);


--
-- Name: session_tontine fks64i0snlmo5itvjqmby5nsxv0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.session_tontine
    ADD CONSTRAINT fks64i0snlmo5itvjqmby5nsxv0 FOREIGN KEY (owner_id_user) REFERENCES public.utilisateur(id_user);


--
-- PostgreSQL database dump complete
--

