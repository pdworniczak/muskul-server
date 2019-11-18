up:
	docker-compose up -d
attach:
	docker-compose exec app bash
run:
	gradle bootRun
