/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { TischesComponentsPage, TischesDeleteDialog, TischesUpdatePage } from './tisches.page-object';

const expect = chai.expect;

describe('Tisches e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let tischesUpdatePage: TischesUpdatePage;
  let tischesComponentsPage: TischesComponentsPage;
  let tischesDeleteDialog: TischesDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Tisches', async () => {
    await navBarPage.goToEntity('tisches');
    tischesComponentsPage = new TischesComponentsPage();
    await browser.wait(ec.visibilityOf(tischesComponentsPage.title), 5000);
    expect(await tischesComponentsPage.getTitle()).to.eq('jhipsterApp.tisches.home.title');
  });

  it('should load create Tisches page', async () => {
    await tischesComponentsPage.clickOnCreateButton();
    tischesUpdatePage = new TischesUpdatePage();
    expect(await tischesUpdatePage.getPageTitle()).to.eq('jhipsterApp.tisches.home.createOrEditLabel');
    await tischesUpdatePage.cancel();
  });

  it('should create and save Tisches', async () => {
    const nbButtonsBeforeCreate = await tischesComponentsPage.countDeleteButtons();

    await tischesComponentsPage.clickOnCreateButton();
    await promise.all([
      tischesUpdatePage.setNameInput('name'),
      tischesUpdatePage.setTischPlatteInput('tischPlatte'),
      tischesUpdatePage.setTischBeinInput('tischBein')
    ]);
    expect(await tischesUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await tischesUpdatePage.getTischPlatteInput()).to.eq('tischPlatte', 'Expected TischPlatte value to be equals to tischPlatte');
    expect(await tischesUpdatePage.getTischBeinInput()).to.eq('tischBein', 'Expected TischBein value to be equals to tischBein');
    await tischesUpdatePage.save();
    expect(await tischesUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await tischesComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Tisches', async () => {
    const nbButtonsBeforeDelete = await tischesComponentsPage.countDeleteButtons();
    await tischesComponentsPage.clickOnLastDeleteButton();

    tischesDeleteDialog = new TischesDeleteDialog();
    expect(await tischesDeleteDialog.getDialogTitle()).to.eq('jhipsterApp.tisches.delete.question');
    await tischesDeleteDialog.clickOnConfirmButton();

    expect(await tischesComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
